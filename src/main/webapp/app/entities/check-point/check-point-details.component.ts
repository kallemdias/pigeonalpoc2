import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICheckPoint } from '@/shared/model/check-point.model';
import CheckPointService from './check-point.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class CheckPointDetails extends Vue {
  @Inject('checkPointService') private checkPointService: () => CheckPointService;
  @Inject('alertService') private alertService: () => AlertService;

  public checkPoint: ICheckPoint = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.checkPointId) {
        vm.retrieveCheckPoint(to.params.checkPointId);
      }
    });
  }

  public retrieveCheckPoint(checkPointId) {
    this.checkPointService()
      .find(checkPointId)
      .then(res => {
        this.checkPoint = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
