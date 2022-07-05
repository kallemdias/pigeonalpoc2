<template>
  <div>
    <h2 id="page-heading" data-cy="VelocityCalculatorHeading">
      <span id="velocity-calculator-heading">Velocity Calculators</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'VelocityCalculatorCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-velocity-calculator"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Velocity Calculator </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && velocityCalculators && velocityCalculators.length === 0">
      <span>No velocityCalculators found</span>
    </div>
    <div class="table-responsive" v-if="velocityCalculators && velocityCalculators.length > 0">
      <table class="table table-striped" aria-describedby="velocityCalculators">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('releaseDateTime')">
              <span>Release Date Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'releaseDateTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrivalDateTime')">
              <span>Arrival Date Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrivalDateTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distanceKM')">
              <span>Distance KM</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distanceKM'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distanceM')">
              <span>Distance M</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distanceM'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('velocity')">
              <span>Velocity</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'velocity'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('velocityDispVal')">
              <span>Velocity Disp Val</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'velocityDispVal'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="velocityCalculator in velocityCalculators" :key="velocityCalculator.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VelocityCalculatorView', params: { velocityCalculatorId: velocityCalculator.id } }">{{
                velocityCalculator.id
              }}</router-link>
            </td>
            <td>{{ velocityCalculator.releaseDateTime | formatDate }}</td>
            <td>{{ velocityCalculator.arrivalDateTime | formatDate }}</td>
            <td>{{ velocityCalculator.distanceKM }}</td>
            <td>{{ velocityCalculator.distanceM }}</td>
            <td>{{ velocityCalculator.velocity }}</td>
            <td>{{ velocityCalculator.velocityDispVal }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'VelocityCalculatorView', params: { velocityCalculatorId: velocityCalculator.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'VelocityCalculatorEdit', params: { velocityCalculatorId: velocityCalculator.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(velocityCalculator)"
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
        ><span id="pigeonalpoc2App.velocityCalculator.delete.question" data-cy="velocityCalculatorDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-velocityCalculator-heading">Are you sure you want to delete this Velocity Calculator?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-velocityCalculator"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeVelocityCalculator()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./velocity-calculator.component.ts"></script>
