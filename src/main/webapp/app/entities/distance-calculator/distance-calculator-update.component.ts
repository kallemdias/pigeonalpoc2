import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, minValue, maxValue, decimal } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IDistanceCalculator, DistanceCalculator } from '@/shared/model/distance-calculator.model';
import DistanceCalculatorService from './distance-calculator.service';
import { LatDirection } from '@/shared/model/enumerations/lat-direction.model';
import { LngDirection } from '@/shared/model/enumerations/lng-direction.model';

const validations: any = {
  distanceCalculator: {
    depLatDeg: {
      numeric,
      min: minValue(0),
      max: maxValue(90),
    },
    depLatMin: {
      numeric,
      min: minValue(0),
      max: maxValue(59),
    },
    depLatSec: {},
    depLatDirection: {},
    depLngDeg: {
      numeric,
      min: minValue(0),
      max: maxValue(180),
    },
    depLngMin: {
      numeric,
      min: minValue(0),
      max: maxValue(59),
    },
    depLngSec: {},
    depLngDirection: {},
    depLatDisplayedValue: {},
    depLngDisplayedValue: {},
    depLatDecimal: {},
    depLngDecimal: {},
    arrLatDeg: {
      numeric,
      min: minValue(0),
      max: maxValue(90),
    },
    arrLatMin: {
      numeric,
      min: minValue(0),
      max: maxValue(59),
    },
    arrLatSec: {},
    arrLatDirection: {},
    arrLngDeg: {
      numeric,
      min: minValue(0),
      max: maxValue(180),
    },
    arrLngMin: {
      numeric,
      min: minValue(0),
      max: maxValue(59),
    },
    arrLngSec: {},
    arrLngDirection: {},
    arrLatDisplayedValue: {},
    arrLngDisplayedValue: {},
    arrLatDecimal: {},
    arrLngDecimal: {},
    distanceInMeters: {
      decimal,
      min: minValue(0),
    },
    distanceInMetersDispVal: {},
    depLink: {},
    arrLink: {},
  },
};

@Component({
  validations,
})
export default class DistanceCalculatorUpdate extends Vue {
  @Inject('distanceCalculatorService') private distanceCalculatorService: () => DistanceCalculatorService;
  @Inject('alertService') private alertService: () => AlertService;

  public distanceCalculator: IDistanceCalculator = new DistanceCalculator();
  public latDirectionValues: string[] = Object.keys(LatDirection);
  public lngDirectionValues: string[] = Object.keys(LngDirection);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.distanceCalculatorId) {
        vm.retrieveDistanceCalculator(to.params.distanceCalculatorId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.distanceCalculator.id) {
      this.distanceCalculatorService()
        .update(this.distanceCalculator)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A DistanceCalculator is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.distanceCalculatorService()
        .create(this.distanceCalculator)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A DistanceCalculator is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveDistanceCalculator(distanceCalculatorId): void {
    this.distanceCalculatorService()
      .find(distanceCalculatorId)
      .then(res => {
        this.distanceCalculator = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
