import { Component, Vue, Inject } from 'vue-property-decorator';

import { IGpsCoordinateCheck } from '@/shared/model/gps-coordinate-check.model';
import GpsCoordinateCheckService from './gps-coordinate-check.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class GpsCoordinateCheckDetails extends Vue {
  @Inject('gpsCoordinateCheckService') private gpsCoordinateCheckService: () => GpsCoordinateCheckService;
  @Inject('alertService') private alertService: () => AlertService;

  public gpsCoordinateCheck: IGpsCoordinateCheck = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.gpsCoordinateCheckId) {
        vm.retrieveGpsCoordinateCheck(to.params.gpsCoordinateCheckId);
      }
    });
  }

  public retrieveGpsCoordinateCheck(gpsCoordinateCheckId) {
    this.gpsCoordinateCheckService()
      .find(gpsCoordinateCheckId)
      .then(res => {
        this.gpsCoordinateCheck = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
