<template>
  <div>
    <h2 id="page-heading" data-cy="DistanceCalculatorHeading">
      <span id="distance-calculator-heading">Distance Calculators</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'DistanceCalculatorCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-distance-calculator"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Distance Calculator </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && distanceCalculators && distanceCalculators.length === 0">
      <span>No distanceCalculators found</span>
    </div>
    <div class="table-responsive" v-if="distanceCalculators && distanceCalculators.length > 0">
      <table class="table table-striped" aria-describedby="distanceCalculators">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLatDeg')">
              <span>Dep Lat Deg</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLatDeg'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLatMin')">
              <span>Dep Lat Min</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLatMin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLatSec')">
              <span>Dep Lat Sec</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLatSec'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLatDirection')">
              <span>Dep Lat Direction</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLatDirection'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLngDeg')">
              <span>Dep Lng Deg</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLngDeg'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLngMin')">
              <span>Dep Lng Min</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLngMin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLngSec')">
              <span>Dep Lng Sec</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLngSec'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLngDirection')">
              <span>Dep Lng Direction</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLngDirection'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLatDisplayedValue')">
              <span>Dep Lat Displayed Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLatDisplayedValue'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLngDisplayedValue')">
              <span>Dep Lng Displayed Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLngDisplayedValue'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLatDecimal')">
              <span>Dep Lat Decimal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLatDecimal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLngDecimal')">
              <span>Dep Lng Decimal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLngDecimal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLatDeg')">
              <span>Arr Lat Deg</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLatDeg'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLatMin')">
              <span>Arr Lat Min</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLatMin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLatSec')">
              <span>Arr Lat Sec</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLatSec'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLatDirection')">
              <span>Arr Lat Direction</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLatDirection'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLngDeg')">
              <span>Arr Lng Deg</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLngDeg'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLngMin')">
              <span>Arr Lng Min</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLngMin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLngSec')">
              <span>Arr Lng Sec</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLngSec'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLngDirection')">
              <span>Arr Lng Direction</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLngDirection'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLatDisplayedValue')">
              <span>Arr Lat Displayed Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLatDisplayedValue'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLngDisplayedValue')">
              <span>Arr Lng Displayed Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLngDisplayedValue'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLatDecimal')">
              <span>Arr Lat Decimal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLatDecimal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLngDecimal')">
              <span>Arr Lng Decimal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLngDecimal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distanceInMeters')">
              <span>Distance In Meters</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distanceInMeters'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distanceInMetersDispVal')">
              <span>Distance In Meters Disp Val</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'distanceInMetersDispVal'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('depLink')">
              <span>Dep Link</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'depLink'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('arrLink')">
              <span>Arr Link</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'arrLink'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="distanceCalculator in distanceCalculators" :key="distanceCalculator.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DistanceCalculatorView', params: { distanceCalculatorId: distanceCalculator.id } }">{{
                distanceCalculator.id
              }}</router-link>
            </td>
            <td>{{ distanceCalculator.depLatDeg }}</td>
            <td>{{ distanceCalculator.depLatMin }}</td>
            <td>{{ distanceCalculator.depLatSec }}</td>
            <td>{{ distanceCalculator.depLatDirection }}</td>
            <td>{{ distanceCalculator.depLngDeg }}</td>
            <td>{{ distanceCalculator.depLngMin }}</td>
            <td>{{ distanceCalculator.depLngSec }}</td>
            <td>{{ distanceCalculator.depLngDirection }}</td>
            <td>{{ distanceCalculator.depLatDisplayedValue }}</td>
            <td>{{ distanceCalculator.depLngDisplayedValue }}</td>
            <td>{{ distanceCalculator.depLatDecimal }}</td>
            <td>{{ distanceCalculator.depLngDecimal }}</td>
            <td>{{ distanceCalculator.arrLatDeg }}</td>
            <td>{{ distanceCalculator.arrLatMin }}</td>
            <td>{{ distanceCalculator.arrLatSec }}</td>
            <td>{{ distanceCalculator.arrLatDirection }}</td>
            <td>{{ distanceCalculator.arrLngDeg }}</td>
            <td>{{ distanceCalculator.arrLngMin }}</td>
            <td>{{ distanceCalculator.arrLngSec }}</td>
            <td>{{ distanceCalculator.arrLngDirection }}</td>
            <td>{{ distanceCalculator.arrLatDisplayedValue }}</td>
            <td>{{ distanceCalculator.arrLngDisplayedValue }}</td>
            <td>{{ distanceCalculator.arrLatDecimal }}</td>
            <td>{{ distanceCalculator.arrLngDecimal }}</td>
            <td>{{ distanceCalculator.distanceInMeters }}</td>
            <td>{{ distanceCalculator.distanceInMetersDispVal }}</td>
            <td>{{ distanceCalculator.depLink }}</td>
            <td>{{ distanceCalculator.arrLink }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'DistanceCalculatorView', params: { distanceCalculatorId: distanceCalculator.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'DistanceCalculatorEdit', params: { distanceCalculatorId: distanceCalculator.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(distanceCalculator)"
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
        ><span id="pigeonalpoc2App.distanceCalculator.delete.question" data-cy="distanceCalculatorDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-distanceCalculator-heading">Are you sure you want to delete this Distance Calculator?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-distanceCalculator"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeDistanceCalculator()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./distance-calculator.component.ts"></script>
