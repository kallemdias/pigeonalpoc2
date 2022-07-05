/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RacingPlanComponent from '@/entities/racing-plan/racing-plan.vue';
import RacingPlanClass from '@/entities/racing-plan/racing-plan.component';
import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('RacingPlan Management Component', () => {
    let wrapper: Wrapper<RacingPlanClass>;
    let comp: RacingPlanClass;
    let racingPlanServiceStub: SinonStubbedInstance<RacingPlanService>;

    beforeEach(() => {
      racingPlanServiceStub = sinon.createStubInstance<RacingPlanService>(RacingPlanService);
      racingPlanServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RacingPlanClass>(RacingPlanComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          racingPlanService: () => racingPlanServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      racingPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRacingPlans();
      await comp.$nextTick();

      // THEN
      expect(racingPlanServiceStub.retrieve.called).toBeTruthy();
      expect(comp.racingPlans[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      racingPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(racingPlanServiceStub.retrieve.called).toBeTruthy();
      expect(comp.racingPlans[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      racingPlanServiceStub.retrieve.reset();
      racingPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(racingPlanServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.racingPlans[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      racingPlanServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(racingPlanServiceStub.retrieve.callCount).toEqual(1);

      comp.removeRacingPlan();
      await comp.$nextTick();

      // THEN
      expect(racingPlanServiceStub.delete.called).toBeTruthy();
      expect(racingPlanServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
