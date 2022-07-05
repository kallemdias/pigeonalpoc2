import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import { IRacingPlan } from '@/shared/model/racing-plan.model';

import { ILeg, Leg } from '@/shared/model/leg.model';
import LegService from './leg.service';
import { LatDirection } from '@/shared/model/enumerations/lat-direction.model';
import { LngDirection } from '@/shared/model/enumerations/lng-direction.model';
import { CheckPointDistance } from '@/shared/model/enumerations/check-point-distance.model';
import { CheckLinePointDistance } from '@/shared/model/enumerations/check-line-point-distance.model';
import { TimeWindow } from '@/shared/model/enumerations/time-window.model';

const validations: any = {
  leg: {
    order: {},
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
    checkPointDistance: {},
    checkLinePointDistance: {},
    timeWindow: {},
  },
};

@Component({
  validations,
})
export default class LegUpdate extends Vue {
  @Inject('legService') private legService: () => LegService;
  @Inject('alertService') private alertService: () => AlertService;

  public leg: ILeg = new Leg();

  @Inject('racingPlanService') private racingPlanService: () => RacingPlanService;

  public racingPlans: IRacingPlan[] = [];
  public latDirectionValues: string[] = Object.keys(LatDirection);
  public lngDirectionValues: string[] = Object.keys(LngDirection);
  public checkPointDistanceValues: string[] = Object.keys(CheckPointDistance);
  public checkLinePointDistanceValues: string[] = Object.keys(CheckLinePointDistance);
  public timeWindowValues: string[] = Object.keys(TimeWindow);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.legId) {
        vm.retrieveLeg(to.params.legId);
      }
      vm.initRelationships();
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
    if (this.leg.id) {
      this.legService()
        .update(this.leg)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Leg is updated with identifier ' + param.id;
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
      this.legService()
        .create(this.leg)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Leg is created with identifier ' + param.id;
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

  public retrieveLeg(legId): void {
    this.legService()
      .find(legId)
      .then(res => {
        this.leg = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.racingPlanService()
      .retrieve()
      .then(res => {
        this.racingPlans = res.data;
      });
  }
}
