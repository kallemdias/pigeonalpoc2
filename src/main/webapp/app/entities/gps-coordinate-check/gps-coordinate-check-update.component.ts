import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IGpsCoordinateCheck, GpsCoordinateCheck } from '@/shared/model/gps-coordinate-check.model';
import GpsCoordinateCheckService from './gps-coordinate-check.service';
import { LatDirection } from '@/shared/model/enumerations/lat-direction.model';
import { LngDirection } from '@/shared/model/enumerations/lng-direction.model';

const validations: any = {
  gpsCoordinateCheck: {
    latDeg: {
      numeric,
      min: minValue(0),
      max: maxValue(90),
    },
    latMin: {
      numeric,
      min: minValue(0),
      max: maxValue(59),
    },
    latSec: {},
    latDirection: {},
    lngDeg: {
      numeric,
      min: minValue(0),
      max: maxValue(180),
    },
    lngMin: {
      numeric,
      min: minValue(0),
      max: maxValue(59),
    },
    lngSec: {},
    lngDirection: {},
    latDisplayedValue: {},
    lngDisplayedValue: {},
    latDecimal: {},
    lngDecimal: {},
    link: {},
  },
};

@Component({
  validations,
})
export default class GpsCoordinateCheckUpdate extends Vue {
  @Inject('gpsCoordinateCheckService') private gpsCoordinateCheckService: () => GpsCoordinateCheckService;
  @Inject('alertService') private alertService: () => AlertService;

  public gpsCoordinateCheck: IGpsCoordinateCheck = new GpsCoordinateCheck();
  public latDirectionValues: string[] = Object.keys(LatDirection);
  public lngDirectionValues: string[] = Object.keys(LngDirection);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.gpsCoordinateCheckId) {
        vm.retrieveGpsCoordinateCheck(to.params.gpsCoordinateCheckId);
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
    if (this.gpsCoordinateCheck.id) {
      this.gpsCoordinateCheckService()
        .update(this.gpsCoordinateCheck)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A GpsCoordinateCheck is updated with identifier ' + param.id;
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
      this.gpsCoordinateCheckService()
        .create(this.gpsCoordinateCheck)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A GpsCoordinateCheck is created with identifier ' + param.id;
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

  public retrieveGpsCoordinateCheck(gpsCoordinateCheckId): void {
    this.gpsCoordinateCheckService()
      .find(gpsCoordinateCheckId)
      .then(res => {
        this.gpsCoordinateCheck = res;
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
