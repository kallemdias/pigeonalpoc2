<template>
  <div>
    <h2 id="page-heading" data-cy="RacingPlanHeading">
      <span id="racing-plan-heading">Racing Plans</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'RacingPlanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-racing-plan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Racing Plan </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && racingPlans && racingPlans.length === 0">
      <span>No racingPlans found</span>
    </div>
    <div class="table-responsive" v-if="racingPlans && racingPlans.length > 0">
      <table class="table table-striped" aria-describedby="racingPlans">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('assocation')">
              <span>Assocation</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'assocation'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('releaseDate')">
              <span>Release Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'releaseDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('releasePoint')">
              <span>Release Point</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'releasePoint'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrivalPoint')">
              <span>Arrival Point</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrivalPoint'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('releasePointDMS')">
              <span>Release Point DMS</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'releasePointDMS'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrivalPointDMS')">
              <span>Arrival Point DMS</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrivalPointDMS'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('releaseMapLink')">
              <span>Release Map Link</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'releaseMapLink'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrivalMapLink')">
              <span>Arrival Map Link</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrivalMapLink'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distance')">
              <span>Distance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distanceDisplayedValue')">
              <span>Distance Displayed Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distanceDisplayedValue'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('checkPointDistance')">
              <span>Check Point Distance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkPointDistance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('checkLinedReporting')">
              <span>Check Lined Reporting</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkLinedReporting'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('checkLinePointDistance')">
              <span>Check Line Point Distance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkLinePointDistance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('timeWindow')">
              <span>Time Window</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'timeWindow'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('generationInProgress')">
              <span>Generation In Progress</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'generationInProgress'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('generated')">
              <span>Generated</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'generated'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reset')">
              <span>Reset</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reset'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('progress')">
              <span>Progress</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'progress'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('user.login')">
              <span>User</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user.login'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('association.id')">
              <span>Association</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'association.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="racingPlan in racingPlans" :key="racingPlan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RacingPlanView', params: { racingPlanId: racingPlan.id } }">{{ racingPlan.id }}</router-link>
            </td>
            <td>{{ racingPlan.name }}</td>
            <td>{{ racingPlan.assocation }}</td>
            <td>{{ racingPlan.releaseDate | formatDate }}</td>
            <td>{{ racingPlan.releasePoint }}</td>
            <td>{{ racingPlan.arrivalPoint }}</td>
            <td>{{ racingPlan.releasePointDMS }}</td>
            <td>{{ racingPlan.arrivalPointDMS }}</td>
            <td>{{ racingPlan.releaseMapLink }}</td>
            <td>{{ racingPlan.arrivalMapLink }}</td>
            <td>{{ racingPlan.distance }}</td>
            <td>{{ racingPlan.distanceDisplayedValue }}</td>
            <td>{{ racingPlan.checkPointDistance }}</td>
            <td>{{ racingPlan.checkLinedReporting }}</td>
            <td>{{ racingPlan.checkLinePointDistance }}</td>
            <td>{{ racingPlan.timeWindow }}</td>
            <td>{{ racingPlan.generationInProgress }}</td>
            <td>{{ racingPlan.generated }}</td>
            <td>{{ racingPlan.reset }}</td>
            <td>{{ racingPlan.progress }}</td>
            <td>
              {{ racingPlan.user ? racingPlan.user.login : '' }}
            </td>
            <td>
              <div v-if="racingPlan.association">
                <router-link :to="{ name: 'AssociationView', params: { associationId: racingPlan.association.id } }">{{
                  racingPlan.association.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RacingPlanView', params: { racingPlanId: racingPlan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RacingPlanEdit', params: { racingPlanId: racingPlan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(racingPlan)"
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
        ><span id="pigeonalpoc2App.racingPlan.delete.question" data-cy="racingPlanDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-racingPlan-heading">Are you sure you want to delete this Racing Plan?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-racingPlan"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeRacingPlan()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./racing-plan.component.ts"></script>
