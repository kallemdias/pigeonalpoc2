/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import AssociationComponent from '@/entities/association/association.vue';
import AssociationClass from '@/entities/association/association.component';
import AssociationService from '@/entities/association/association.service';
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
  describe('Association Management Component', () => {
    let wrapper: Wrapper<AssociationClass>;
    let comp: AssociationClass;
    let associationServiceStub: SinonStubbedInstance<AssociationService>;

    beforeEach(() => {
      associationServiceStub = sinon.createStubInstance<AssociationService>(AssociationService);
      associationServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<AssociationClass>(AssociationComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          associationService: () => associationServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      associationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllAssociations();
      await comp.$nextTick();

      // THEN
      expect(associationServiceStub.retrieve.called).toBeTruthy();
      expect(comp.associations[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      associationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(associationServiceStub.retrieve.called).toBeTruthy();
      expect(comp.associations[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      associationServiceStub.retrieve.reset();
      associationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(associationServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.associations[0]).toEqual(expect.objectContaining({ id: 123 }));
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
      associationServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(associationServiceStub.retrieve.callCount).toEqual(1);

      comp.removeAssociation();
      await comp.$nextTick();

      // THEN
      expect(associationServiceStub.delete.called).toBeTruthy();
      expect(associationServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
