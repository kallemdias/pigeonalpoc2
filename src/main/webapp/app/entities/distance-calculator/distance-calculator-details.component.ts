import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDistanceCalculator } from '@/shared/model/distance-calculator.model';
import DistanceCalculatorService from './distance-calculator.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class DistanceCalculatorDetails extends Vue {
  @Inject('distanceCalculatorService') private distanceCalculatorService: () => DistanceCalculatorService;
  @Inject('alertService') private alertService: () => AlertService;

  public distanceCalculator: IDistanceCalculator = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.distanceCalculatorId) {
        vm.retrieveDistanceCalculator(to.params.distanceCalculatorId);
      }
    });
  }

  public retrieveDistanceCalculator(distanceCalculatorId) {
    this.distanceCalculatorService()
      .find(distanceCalculatorId)
      .then(res => {
        this.distanceCalculator = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
