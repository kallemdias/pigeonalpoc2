/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RelevanceHeaderUpdateComponent from '@/entities/relevance-header/relevance-header-update.vue';
import RelevanceHeaderClass from '@/entities/relevance-header/relevance-header-update.component';
import RelevanceHeaderService from '@/entities/relevance-header/relevance-header.service';

import WeatherDataPointService from '@/entities/weather-data-point/weather-data-point.service';
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
  describe('RelevanceHeader Management Update Component', () => {
    let wrapper: Wrapper<RelevanceHeaderClass>;
    let comp: RelevanceHeaderClass;
    let relevanceHeaderServiceStub: SinonStubbedInstance<RelevanceHeaderService>;

    beforeEach(() => {
      relevanceHeaderServiceStub = sinon.createStubInstance<RelevanceHeaderService>(RelevanceHeaderService);

      wrapper = shallowMount<RelevanceHeaderClass>(RelevanceHeaderUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          relevanceHeaderService: () => relevanceHeaderServiceStub,
          alertService: () => new AlertService(),

          weatherDataPointService: () =>
            sinon.createStubInstance<WeatherDataPointService>(WeatherDataPointService, {
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
        comp.relevanceHeader = entity;
        relevanceHeaderServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(relevanceHeaderServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.relevanceHeader = entity;
        relevanceHeaderServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(relevanceHeaderServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRelevanceHeader = { id: 123 };
        relevanceHeaderServiceStub.find.resolves(foundRelevanceHeader);
        relevanceHeaderServiceStub.retrieve.resolves([foundRelevanceHeader]);

        // WHEN
        comp.beforeRouteEnter({ params: { relevanceHeaderId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.relevanceHeader).toBe(foundRelevanceHeader);
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
