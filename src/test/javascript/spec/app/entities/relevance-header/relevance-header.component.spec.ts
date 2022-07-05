/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RelevanceHeaderComponent from '@/entities/relevance-header/relevance-header.vue';
import RelevanceHeaderClass from '@/entities/relevance-header/relevance-header.component';
import RelevanceHeaderService from '@/entities/relevance-header/relevance-header.service';
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
  describe('RelevanceHeader Management Component', () => {
    let wrapper: Wrapper<RelevanceHeaderClass>;
    let comp: RelevanceHeaderClass;
    let relevanceHeaderServiceStub: SinonStubbedInstance<RelevanceHeaderService>;

    beforeEach(() => {
      relevanceHeaderServiceStub = sinon.createStubInstance<RelevanceHeaderService>(RelevanceHeaderService);
      relevanceHeaderServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RelevanceHeaderClass>(RelevanceHeaderComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          relevanceHeaderService: () => relevanceHeaderServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      relevanceHeaderServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRelevanceHeaders();
      await comp.$nextTick();

      // THEN
      expect(relevanceHeaderServiceStub.retrieve.called).toBeTruthy();
      expect(comp.relevanceHeaders[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      relevanceHeaderServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(relevanceHeaderServiceStub.retrieve.called).toBeTruthy();
      expect(comp.relevanceHeaders[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      relevanceHeaderServiceStub.retrieve.reset();
      relevanceHeaderServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(relevanceHeaderServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.relevanceHeaders[0]).toEqual(expect.objectContaining({ id: 123 }));
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
      relevanceHeaderServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(relevanceHeaderServiceStub.retrieve.callCount).toEqual(1);

      comp.removeRelevanceHeader();
      await comp.$nextTick();

      // THEN
      expect(relevanceHeaderServiceStub.delete.called).toBeTruthy();
      expect(relevanceHeaderServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
