<template>
  <div>
    <h2 id="page-heading" data-cy="AssociationHeading">
      <span id="association-heading">Associations</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'AssociationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-association"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Association </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && associations && associations.length === 0">
      <span>No associations found</span>
    </div>
    <div class="table-responsive" v-if="associations && associations.length > 0">
      <table class="table table-striped" aria-describedby="associations">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shortName')">
              <span>Short Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shortName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span>Email</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phone')">
              <span>Phone</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phone'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('addressLine1')">
              <span>Address Line 1</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'addressLine1'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('addressLine2')">
              <span>Address Line 2</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'addressLine2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('city')">
              <span>City</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'city'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('country')">
              <span>Country</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'country'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fbLink')">
              <span>Fb Link</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fbLink'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="association in associations" :key="association.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AssociationView', params: { associationId: association.id } }">{{ association.id }}</router-link>
            </td>
            <td>{{ association.name }}</td>
            <td>{{ association.shortName }}</td>
            <td>{{ association.email }}</td>
            <td>{{ association.phone }}</td>
            <td>{{ association.addressLine1 }}</td>
            <td>{{ association.addressLine2 }}</td>
            <td>{{ association.city }}</td>
            <td>{{ association.country }}</td>
            <td>{{ association.fbLink }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'AssociationView', params: { associationId: association.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'AssociationEdit', params: { associationId: association.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(association)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
        <infinite-loading
          ref="infiniteLoading"
          v-if="totalItems > itemsPerPage"
          :identifier="infiniteId"
          slot="append"
          @infinite="loadMore"
          force-use-infinite-wrapper=".el-table__body-wrapper"
          :distance="20"
        >
        </infinite-loading>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="pigeonalpoc2App.association.delete.question" data-cy="associationDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-association-heading">Are you sure you want to delete this Association?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-association"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeAssociation()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./association.component.ts"></script>
