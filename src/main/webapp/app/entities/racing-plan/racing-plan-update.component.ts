import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, decimal, minValue } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import WeatherReportService from '@/entities/weather-report/weather-report.service';
import { IWeatherReport } from '@/shared/model/weather-report.model';

import LegService from '@/entities/leg/leg.service';
import { ILeg } from '@/shared/model/leg.model';

import YcLogEntryService from '@/entities/yc-log-entry/yc-log-entry.service';
import { IYcLogEntry } from '@/shared/model/yc-log-entry.model';

import UserService from '@/entities/user/user.service';

import AssociationService from '@/entities/association/association.service';
import { IAssociation } from '@/shared/model/association.model';

import { IRacingPlan, RacingPlan } from '@/shared/model/racing-plan.model';
import RacingPlanService from './racing-plan.service';
import { CheckPointDistance } from '@/shared/model/enumerations/check-point-distance.model';
import { CheckLinePointDistance } from '@/shared/model/enumerations/check-line-point-distance.model';
import { TimeWindow } from '@/shared/model/enumerations/time-window.model';

const validations: any = {
  racingPlan: {
    name: {
      required,
    },
    assocation: {
      required,
    },
    releaseDate: {},
    releasePoint: {},
    arrivalPoint: {},
    releasePointDMS: {},
    arrivalPointDMS: {},
    releaseMapLink: {},
    arrivalMapLink: {},
    distance: {
      decimal,
      min: minValue(0),
    },
    distanceDisplayedValue: {},
    checkPointDistance: {},
    checkLinedReporting: {},
    checkLinePointDistance: {},
    timeWindow: {},
    generationInProgress: {},
    generated: {},
    reset: {},
    progress: {},
  },
};

@Component({
  validations,
})
export default class RacingPlanUpdate extends Vue {
  @Inject('racingPlanService') private racingPlanService: () => RacingPlanService;
  @Inject('alertService') private alertService: () => AlertService;

  public racingPlan: IRacingPlan = new RacingPlan();

  @Inject('weatherReportService') private weatherReportService: () => WeatherReportService;

  public weatherReports: IWeatherReport[] = [];

  @Inject('legService') private legService: () => LegService;

  public legs: ILeg[] = [];

  @Inject('ycLogEntryService') private ycLogEntryService: () => YcLogEntryService;

  public ycLogEntries: IYcLogEntry[] = [];

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];

  @Inject('associationService') private associationService: () => AssociationService;

  public associations: IAssociation[] = [];
  public checkPointDistanceValues: string[] = Object.keys(CheckPointDistance);
  public checkLinePointDistanceValues: string[] = Object.keys(CheckLinePointDistance);
  public timeWindowValues: string[] = Object.keys(TimeWindow);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.racingPlanId) {
        vm.retrieveRacingPlan(to.params.racingPlanId);
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
    if (this.racingPlan.id) {
      this.racingPlanService()
        .update(this.racingPlan)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A RacingPlan is updated with identifier ' + param.id;
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
      this.racingPlanService()
        .create(this.racingPlan)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A RacingPlan is created with identifier ' + param.id;
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
      this.racingPlan[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.racingPlan[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.racingPlan[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.racingPlan[field] = null;
    }
  }

  public retrieveRacingPlan(racingPlanId): void {
    this.racingPlanService()
      .find(racingPlanId)
      .then(res => {
        res.releaseDate = new Date(res.releaseDate);
        this.racingPlan = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.weatherReportService()
      .retrieve()
      .then(res => {
        this.weatherReports = res.data;
      });
    this.legService()
      .retrieve()
      .then(res => {
        this.legs = res.data;
      });
    this.ycLogEntryService()
      .retrieve()
      .then(res => {
        this.ycLogEntries = res.data;
      });

    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
    this.associationService()
      .retrieve()
      .then(res => {
        this.associations = res.data;
      });
  }
}
