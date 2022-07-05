import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRacingPlan } from '@/shared/model/racing-plan.model';
import RacingPlanService from './racing-plan.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class RacingPlanDetails extends Vue {
  @Inject('racingPlanService') private racingPlanService: () => RacingPlanService;
  @Inject('alertService') private alertService: () => AlertService;

  public racingPlan: IRacingPlan = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.racingPlanId) {
        vm.retrieveRacingPlan(to.params.racingPlanId);
      }
    });
  }

  public retrieveRacingPlan(racingPlanId) {
    this.racingPlanService()
      .find(racingPlanId)
      .then(res => {
        this.racingPlan = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
