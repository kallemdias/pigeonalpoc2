import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { IVelocityCalculator, VelocityCalculator } from '@/shared/model/velocity-calculator.model';
import VelocityCalculatorService from './velocity-calculator.service';

const validations: any = {
  velocityCalculator: {
    releaseDateTime: {},
    arrivalDateTime: {},
    distanceKM: {},
    distanceM: {},
    velocity: {},
    velocityDispVal: {},
  },
};

@Component({
  validations,
})
export default class VelocityCalculatorUpdate extends Vue {
  @Inject('velocityCalculatorService') private velocityCalculatorService: () => VelocityCalculatorService;
  @Inject('alertService') private alertService: () => AlertService;

  public velocityCalculator: IVelocityCalculator = new VelocityCalculator();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.velocityCalculatorId) {
        vm.retrieveVelocityCalculator(to.params.velocityCalculatorId);
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
    if (this.velocityCalculator.id) {
      this.velocityCalculatorService()
        .update(this.velocityCalculator)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A VelocityCalculator is updated with identifier ' + param.id;
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
      this.velocityCalculatorService()
        .create(this.velocityCalculator)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A VelocityCalculator is created with identifier ' + param.id;
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.velocityCalculator[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.velocityCalculator[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.velocityCalculator[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.velocityCalculator[field] = null;
    }
  }

  public retrieveVelocityCalculator(velocityCalculatorId): void {
    this.velocityCalculatorService()
      .find(velocityCalculatorId)
      .then(res => {
        res.releaseDateTime = new Date(res.releaseDateTime);
        res.arrivalDateTime = new Date(res.arrivalDateTime);
        this.velocityCalculator = res;
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
