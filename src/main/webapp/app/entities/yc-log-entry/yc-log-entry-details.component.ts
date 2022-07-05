import { Component, Vue, Inject } from 'vue-property-decorator';

import { IYcLogEntry } from '@/shared/model/yc-log-entry.model';
import YcLogEntryService from './yc-log-entry.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class YcLogEntryDetails extends Vue {
  @Inject('ycLogEntryService') private ycLogEntryService: () => YcLogEntryService;
  @Inject('alertService') private alertService: () => AlertService;

  public ycLogEntry: IYcLogEntry = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ycLogEntryId) {
        vm.retrieveYcLogEntry(to.params.ycLogEntryId);
      }
    });
  }

  public retrieveYcLogEntry(ycLogEntryId) {
    this.ycLogEntryService()
      .find(ycLogEntryId)
      .then(res => {
        this.ycLogEntry = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
