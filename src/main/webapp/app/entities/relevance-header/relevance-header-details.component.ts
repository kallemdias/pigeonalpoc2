import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRelevanceHeader } from '@/shared/model/relevance-header.model';
import RelevanceHeaderService from './relevance-header.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class RelevanceHeaderDetails extends Vue {
  @Inject('relevanceHeaderService') private relevanceHeaderService: () => RelevanceHeaderService;
  @Inject('alertService') private alertService: () => AlertService;

  public relevanceHeader: IRelevanceHeader = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.relevanceHeaderId) {
        vm.retrieveRelevanceHeader(to.params.relevanceHeaderId);
      }
    });
  }

  public retrieveRelevanceHeader(relevanceHeaderId) {
    this.relevanceHeaderService()
      .find(relevanceHeaderId)
      .then(res => {
        this.relevanceHeader = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
