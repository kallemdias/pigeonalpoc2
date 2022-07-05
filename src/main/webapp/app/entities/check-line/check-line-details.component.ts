import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICheckLine } from '@/shared/model/check-line.model';
import CheckLineService from './check-line.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class CheckLineDetails extends Vue {
  @Inject('checkLineService') private checkLineService: () => CheckLineService;
  @Inject('alertService') private alertService: () => AlertService;

  public checkLine: ICheckLine = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.checkLineId) {
        vm.retrieveCheckLine(to.params.checkLineId);
      }
    });
  }

  public retrieveCheckLine(checkLineId) {
    this.checkLineService()
      .find(checkLineId)
      .then(res => {
        this.checkLine = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
