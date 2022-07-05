import { Component, Vue, Inject } from 'vue-property-decorator';

import { IVelocityCalculator } from '@/shared/model/velocity-calculator.model';
import VelocityCalculatorService from './velocity-calculator.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class VelocityCalculatorDetails extends Vue {
  @Inject('velocityCalculatorService') private velocityCalculatorService: () => VelocityCalculatorService;
  @Inject('alertService') private alertService: () => AlertService;

  public velocityCalculator: IVelocityCalculator = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.velocityCalculatorId) {
        vm.retrieveVelocityCalculator(to.params.velocityCalculatorId);
      }
    });
  }

  public retrieveVelocityCalculator(velocityCalculatorId) {
    this.velocityCalculatorService()
      .find(velocityCalculatorId)
      .then(res => {
        this.velocityCalculator = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
