import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import { IRacingPlan } from '@/shared/model/racing-plan.model';

import { IAssociation, Association } from '@/shared/model/association.model';
import AssociationService from './association.service';

const validations: any = {
  association: {
    name: {
      required,
    },
    shortName: {
      required,
    },
    email: {},
    phone: {},
    addressLine1: {},
    addressLine2: {},
    city: {},
    country: {},
    fbLink: {},
  },
};

@Component({
  validations,
})
export default class AssociationUpdate extends Vue {
  @Inject('associationService') private associationService: () => AssociationService;
  @Inject('alertService') private alertService: () => AlertService;

  public association: IAssociation = new Association();

  @Inject('racingPlanService') private racingPlanService: () => RacingPlanService;

  public racingPlans: IRacingPlan[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.associationId) {
        vm.retrieveAssociation(to.params.associationId);
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
    if (this.association.id) {
      this.associationService()
        .update(this.association)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Association is updated with identifier ' + param.id;
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
      this.associationService()
        .create(this.association)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Association is created with identifier ' + param.id;
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

  public retrieveAssociation(associationId): void {
    this.associationService()
      .find(associationId)
      .then(res => {
        this.association = res;
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
