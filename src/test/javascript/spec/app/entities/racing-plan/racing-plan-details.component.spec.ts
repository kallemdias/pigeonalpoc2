/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RacingPlanDetailComponent from '@/entities/racing-plan/racing-plan-details.vue';
import RacingPlanClass from '@/entities/racing-plan/racing-plan-details.component';
import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RacingPlan Management Detail Component', () => {
    let wrapper: Wrapper<RacingPlanClass>;
    let comp: RacingPlanClass;
    let racingPlanServiceStub: SinonStubbedInstance<RacingPlanService>;

    beforeEach(() => {
      racingPlanServiceStub = sinon.createStubInstance<RacingPlanService>(RacingPlanService);

      wrapper = shallowMount<RacingPlanClass>(RacingPlanDetailComponent, {
        store,
        localVue,
        router,
        provide: { racingPlanService: () => racingPlanServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRacingPlan = { id: 123 };
        racingPlanServiceStub.find.resolves(foundRacingPlan);

        // WHEN
        comp.retrieveRacingPlan(123);
        await comp.$nextTick();

        // THEN
        expect(comp.racingPlan).toBe(foundRacingPlan);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRacingPlan = { id: 123 };
        racingPlanServiceStub.find.resolves(foundRacingPlan);

        // WHEN
        comp.beforeRouteEnter({ params: { racingPlanId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.racingPlan).toBe(foundRacingPlan);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
