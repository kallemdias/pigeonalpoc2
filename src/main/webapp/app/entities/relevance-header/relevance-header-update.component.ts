import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import WeatherDataPointService from '@/entities/weather-data-point/weather-data-point.service';
import { IWeatherDataPoint } from '@/shared/model/weather-data-point.model';

import { IRelevanceHeader, RelevanceHeader } from '@/shared/model/relevance-header.model';
import RelevanceHeaderService from './relevance-header.service';

const validations: any = {
  relevanceHeader: {
    midnight: {},
    one: {},
    two: {},
    three: {},
    four: {},
    five: {},
    six: {},
    seven: {},
    eight: {},
    nine: {},
    ten: {},
    eleven: {},
    twelve: {},
    thirteen: {},
    fourteen: {},
    fifteen: {},
    sixteen: {},
    seventeen: {},
    eighteen: {},
    nineteen: {},
    twenty: {},
    twentyOne: {},
    twentyTwo: {},
    twentyThree: {},
  },
};

@Component({
  validations,
})
export default class RelevanceHeaderUpdate extends Vue {
  @Inject('relevanceHeaderService') private relevanceHeaderService: () => RelevanceHeaderService;
  @Inject('alertService') private alertService: () => AlertService;

  public relevanceHeader: IRelevanceHeader = new RelevanceHeader();

  @Inject('weatherDataPointService') private weatherDataPointService: () => WeatherDataPointService;

  public weatherDataPoints: IWeatherDataPoint[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.relevanceHeaderId) {
        vm.retrieveRelevanceHeader(to.params.relevanceHeaderId);
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
    if (this.relevanceHeader.id) {
      this.relevanceHeaderService()
        .update(this.relevanceHeader)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A RelevanceHeader is updated with identifier ' + param.id;
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
      this.relevanceHeaderService()
        .create(this.relevanceHeader)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A RelevanceHeader is created with identifier ' + param.id;
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

  public retrieveRelevanceHeader(relevanceHeaderId): void {
    this.relevanceHeaderService()
      .find(relevanceHeaderId)
      .then(res => {
        this.relevanceHeader = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.weatherDataPointService()
      .retrieve()
      .then(res => {
        this.weatherDataPoints = res.data;
      });
  }
}
