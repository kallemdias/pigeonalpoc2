import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, minValue, decimal } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import CheckPointService from '@/entities/check-point/check-point.service';
import { ICheckPoint } from '@/shared/model/check-point.model';

import WeatherReportService from '@/entities/weather-report/weather-report.service';
import { IWeatherReport } from '@/shared/model/weather-report.model';

import { ICheckLine, CheckLine } from '@/shared/model/check-line.model';
import CheckLineService from './check-line.service';

const validations: any = {
  checkLine: {
    order: {
      numeric,
      min: minValue(0),
    },
    dateTime: {},
    locationName: {},
    latDecimal: {},
    lngDecimal: {},
    distance: {
      decimal,
      min: minValue(0),
    },
    distanceDisplayedValue: {},
    link: {},
  },
};

@Component({
  validations,
})
export default class CheckLineUpdate extends Vue {
  @Inject('checkLineService') private checkLineService: () => CheckLineService;
  @Inject('alertService') private alertService: () => AlertService;

  public checkLine: ICheckLine = new CheckLine();

  @Inject('checkPointService') private checkPointService: () => CheckPointService;

  public checkPoints: ICheckPoint[] = [];

  @Inject('weatherReportService') private weatherReportService: () => WeatherReportService;

  public weatherReports: IWeatherReport[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.checkLineId) {
        vm.retrieveCheckLine(to.params.checkLineId);
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
    if (this.checkLine.id) {
      this.checkLineService()
        .update(this.checkLine)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A CheckLine is updated with identifier ' + param.id;
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
      this.checkLineService()
        .create(this.checkLine)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A CheckLine is created with identifier ' + param.id;
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
      this.checkLine[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.checkLine[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.checkLine[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.checkLine[field] = null;
    }
  }

  public retrieveCheckLine(checkLineId): void {
    this.checkLineService()
      .find(checkLineId)
      .then(res => {
        res.dateTime = new Date(res.dateTime);
        this.checkLine = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.checkPointService()
      .retrieve()
      .then(res => {
        this.checkPoints = res.data;
      });
    this.weatherReportService()
      .retrieve()
      .then(res => {
        this.weatherReports = res.data;
      });
  }
}
