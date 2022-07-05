/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import LegUpdateComponent from '@/entities/leg/leg-update.vue';
import LegClass from '@/entities/leg/leg-update.component';
import LegService from '@/entities/leg/leg.service';

import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Leg Management Update Component', () => {
    let wrapper: Wrapper<LegClass>;
    let comp: LegClass;
    let legServiceStub: SinonStubbedInstance<LegService>;

    beforeEach(() => {
      legServiceStub = sinon.createStubInstance<LegService>(LegService);

      wrapper = shallowMount<LegClass>(LegUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          legService: () => legServiceStub,
          alertService: () => new AlertService(),

          racingPlanService: () =>
            sinon.createStubInstance<RacingPlanService>(RacingPlanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.leg = entity;
        legServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(legServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.leg = entity;
        legServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(legServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLeg = { id: 123 };
        legServiceStub.find.resolves(foundLeg);
        legServiceStub.retrieve.resolves([foundLeg]);

        // WHEN
        comp.beforeRouteEnter({ params: { legId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.leg).toBe(foundLeg);
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
