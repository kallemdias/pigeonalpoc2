import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILeg } from '@/shared/model/leg.model';
import LegService from './leg.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class LegDetails extends Vue {
  @Inject('legService') private legService: () => LegService;
  @Inject('alertService') private alertService: () => AlertService;

  public leg: ILeg = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.legId) {
        vm.retrieveLeg(to.params.legId);
      }
    });
  }

  public retrieveLeg(legId) {
    this.legService()
      .find(legId)
      .then(res => {
        this.leg = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
