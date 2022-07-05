import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, minValue } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import CheckLineService from '@/entities/check-line/check-line.service';
import { ICheckLine } from '@/shared/model/check-line.model';

import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import { IRacingPlan } from '@/shared/model/racing-plan.model';

import { IWeatherReport, WeatherReport } from '@/shared/model/weather-report.model';
import WeatherReportService from './weather-report.service';

const validations: any = {
  weatherReport: {
    initiated: {},
    releaseDateTime: {},
    checkpointsDistance: {
      numeric,
      min: minValue(5),
    },
    alerts: {},
  },
};

@Component({
  validations,
})
export default class WeatherReportUpdate extends Vue {
  @Inject('weatherReportService') private weatherReportService: () => WeatherReportService;
  @Inject('alertService') private alertService: () => AlertService;

  public weatherReport: IWeatherReport = new WeatherReport();

  @Inject('checkLineService') private checkLineService: () => CheckLineService;

  public checkLines: ICheckLine[] = [];

  @Inject('racingPlanService') private racingPlanService: () => RacingPlanService;

  public racingPlans: IRacingPlan[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.weatherReportId) {
        vm.retrieveWeatherReport(to.params.weatherReportId);
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
    if (this.weatherReport.id) {
      this.weatherReportService()
        .update(this.weatherReport)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A WeatherReport is updated with identifier ' + param.id;
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
      this.weatherReportService()
        .create(this.weatherReport)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A WeatherReport is created with identifier ' + param.id;
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
      this.weatherReport[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.weatherReport[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.weatherReport[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.weatherReport[field] = null;
    }
  }

  public retrieveWeatherReport(weatherReportId): void {
    this.weatherReportService()
      .find(weatherReportId)
      .then(res => {
        res.initiated = new Date(res.initiated);
        res.releaseDateTime = new Date(res.releaseDateTime);
        this.weatherReport = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.checkLineService()
      .retrieve()
      .then(res => {
        this.checkLines = res.data;
      });
    this.racingPlanService()
      .retrieve()
      .then(res => {
        this.racingPlans = res.data;
      });
  }
}
