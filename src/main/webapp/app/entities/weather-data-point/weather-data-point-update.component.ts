import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import RelevanceHeaderService from '@/entities/relevance-header/relevance-header.service';
import { IRelevanceHeader } from '@/shared/model/relevance-header.model';

import CheckPointService from '@/entities/check-point/check-point.service';
import { ICheckPoint } from '@/shared/model/check-point.model';

import { IWeatherDataPoint, WeatherDataPoint } from '@/shared/model/weather-data-point.model';
import WeatherDataPointService from './weather-data-point.service';
import { WeatherProperty } from '@/shared/model/enumerations/weather-property.model';

const validations: any = {
  weatherDataPoint: {
    property: {},
    midNight: {},
    migNightRelevance: {},
    one: {},
    oneRelevance: {},
    two: {},
    twoRelevance: {},
    three: {},
    threeRelevance: {},
    four: {},
    fourRelevance: {},
    five: {},
    fiveRelevance: {},
    six: {},
    sixRelevance: {},
    seven: {},
    sevenRelevance: {},
    eight: {},
    eightRelevance: {},
    nine: {},
    nineRelevance: {},
    ten: {},
    tenRelevance: {},
    eleven: {},
    elevenRelevance: {},
    twelve: {},
    twelveRelevance: {},
    thirteen: {},
    thirteenRelevance: {},
    fourteen: {},
    fourteenRelevance: {},
    fifteen: {},
    fifteenRelevance: {},
    sixteen: {},
    sixteenRelevance: {},
    seventeen: {},
    seventeenRelevance: {},
    eighteen: {},
    eighteenRelevance: {},
    nineteen: {},
    nineteenRelevance: {},
    twenty: {},
    twentyRelevance: {},
    twentyOne: {},
    twentyOneRelevance: {},
    twentyTwo: {},
    twentyTwoRelevance: {},
    twentyThree: {},
  },
};

@Component({
  validations,
})
export default class WeatherDataPointUpdate extends Vue {
  @Inject('weatherDataPointService') private weatherDataPointService: () => WeatherDataPointService;
  @Inject('alertService') private alertService: () => AlertService;

  public weatherDataPoint: IWeatherDataPoint = new WeatherDataPoint();

  @Inject('relevanceHeaderService') private relevanceHeaderService: () => RelevanceHeaderService;

  public relevanceHeaders: IRelevanceHeader[] = [];

  @Inject('checkPointService') private checkPointService: () => CheckPointService;

  public checkPoints: ICheckPoint[] = [];
  public weatherPropertyValues: string[] = Object.keys(WeatherProperty);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.weatherDataPointId) {
        vm.retrieveWeatherDataPoint(to.params.weatherDataPointId);
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
    if (this.weatherDataPoint.id) {
      this.weatherDataPointService()
        .update(this.weatherDataPoint)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A WeatherDataPoint is updated with identifier ' + param.id;
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
      this.weatherDataPointService()
        .create(this.weatherDataPoint)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A WeatherDataPoint is created with identifier ' + param.id;
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

  public retrieveWeatherDataPoint(weatherDataPointId): void {
    this.weatherDataPointService()
      .find(weatherDataPointId)
      .then(res => {
        this.weatherDataPoint = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.relevanceHeaderService()
      .retrieve()
      .then(res => {
        this.relevanceHeaders = res.data;
      });
    this.checkPointService()
      .retrieve()
      .then(res => {
        this.checkPoints = res.data;
      });
  }
}
