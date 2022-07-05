/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DistanceCalculatorUpdateComponent from '@/entities/distance-calculator/distance-calculator-update.vue';
import DistanceCalculatorClass from '@/entities/distance-calculator/distance-calculator-update.component';
import DistanceCalculatorService from '@/entities/distance-calculator/distance-calculator.service';

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
  describe('DistanceCalculator Management Update Component', () => {
    let wrapper: Wrapper<DistanceCalculatorClass>;
    let comp: DistanceCalculatorClass;
    let distanceCalculatorServiceStub: SinonStubbedInstance<DistanceCalculatorService>;

    beforeEach(() => {
      distanceCalculatorServiceStub = sinon.createStubInstance<DistanceCalculatorService>(DistanceCalculatorService);

      wrapper = shallowMount<DistanceCalculatorClass>(DistanceCalculatorUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          distanceCalculatorService: () => distanceCalculatorServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.distanceCalculator = entity;
        distanceCalculatorServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(distanceCalculatorServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.distanceCalculator = entity;
        distanceCalculatorServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(distanceCalculatorServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDistanceCalculator = { id: 123 };
        distanceCalculatorServiceStub.find.resolves(foundDistanceCalculator);
        distanceCalculatorServiceStub.retrieve.resolves([foundDistanceCalculator]);

        // WHEN
        comp.beforeRouteEnter({ params: { distanceCalculatorId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.distanceCalculator).toBe(foundDistanceCalculator);
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
