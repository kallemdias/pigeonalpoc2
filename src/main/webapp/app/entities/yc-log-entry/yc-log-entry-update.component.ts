import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import { IRacingPlan } from '@/shared/model/racing-plan.model';

import { IYcLogEntry, YcLogEntry } from '@/shared/model/yc-log-entry.model';
import YcLogEntryService from './yc-log-entry.service';
import { LogType } from '@/shared/model/enumerations/log-type.model';

const validations: any = {
  ycLogEntry: {
    created: {},
    serviceName: {},
    method: {},
    step: {},
    discription: {},
    logType: {},
  },
};

@Component({
  validations,
})
export default class YcLogEntryUpdate extends Vue {
  @Inject('ycLogEntryService') private ycLogEntryService: () => YcLogEntryService;
  @Inject('alertService') private alertService: () => AlertService;

  public ycLogEntry: IYcLogEntry = new YcLogEntry();

  @Inject('racingPlanService') private racingPlanService: () => RacingPlanService;

  public racingPlans: IRacingPlan[] = [];
  public logTypeValues: string[] = Object.keys(LogType);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ycLogEntryId) {
        vm.retrieveYcLogEntry(to.params.ycLogEntryId);
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
    if (this.ycLogEntry.id) {
      this.ycLogEntryService()
        .update(this.ycLogEntry)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A YcLogEntry is updated with identifier ' + param.id;
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
      this.ycLogEntryService()
        .create(this.ycLogEntry)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A YcLogEntry is created with identifier ' + param.id;
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
      this.ycLogEntry[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.ycLogEntry[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.ycLogEntry[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.ycLogEntry[field] = null;
    }
  }

  public retrieveYcLogEntry(ycLogEntryId): void {
    this.ycLogEntryService()
      .find(ycLogEntryId)
      .then(res => {
        res.created = new Date(res.created);
        this.ycLogEntry = res;
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
