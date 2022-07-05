<template>
  <div>
    <h2 id="page-heading" data-cy="LegHeading">
      <span id="leg-heading">Legs</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'LegCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-leg">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Leg </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && legs && legs.length === 0">
      <span>No legs found</span>
    </div>
    <div class="table-responsive" v-if="legs && legs.length > 0">
      <table class="table table-striped" aria-describedby="legs">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('order')">
              <span>Order</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'order'"></jhi-sort-indicator>
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
            <th scope="row" v-on:click="changeOrder('checkPointDistance')">
              <span>Check Point Distance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkPointDistance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('checkLinePointDistance')">
              <span>Check Line Point Distance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkLinePointDistance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('timeWindow')">
              <span>Time Window</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'timeWindow'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('racingPlan.id')">
              <span>Racing Plan</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'racingPlan.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="leg in legs" :key="leg.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LegView', params: { legId: leg.id } }">{{ leg.id }}</router-link>
            </td>
            <td>{{ leg.order }}</td>
            <td>{{ leg.depLatDeg }}</td>
            <td>{{ leg.depLatMin }}</td>
            <td>{{ leg.depLatSec }}</td>
            <td>{{ leg.depLatDirection }}</td>
            <td>{{ leg.depLngDeg }}</td>
            <td>{{ leg.depLngMin }}</td>
            <td>{{ leg.depLngSec }}</td>
            <td>{{ leg.depLngDirection }}</td>
            <td>{{ leg.depLatDisplayedValue }}</td>
            <td>{{ leg.depLngDisplayedValue }}</td>
            <td>{{ leg.depLatDecimal }}</td>
            <td>{{ leg.depLngDecimal }}</td>
            <td>{{ leg.arrLatDeg }}</td>
            <td>{{ leg.arrLatMin }}</td>
            <td>{{ leg.arrLatSec }}</td>
            <td>{{ leg.arrLatDirection }}</td>
            <td>{{ leg.arrLngDeg }}</td>
            <td>{{ leg.arrLngMin }}</td>
            <td>{{ leg.arrLngSec }}</td>
            <td>{{ leg.arrLngDirection }}</td>
            <td>{{ leg.arrLatDisplayedValue }}</td>
            <td>{{ leg.arrLngDisplayedValue }}</td>
            <td>{{ leg.arrLatDecimal }}</td>
            <td>{{ leg.arrLngDecimal }}</td>
            <td>{{ leg.checkPointDistance }}</td>
            <td>{{ leg.checkLinePointDistance }}</td>
            <td>{{ leg.timeWindow }}</td>
            <td>
              <div v-if="leg.racingPlan">
                <router-link :to="{ name: 'RacingPlanView', params: { racingPlanId: leg.racingPlan.id } }">{{
                  leg.racingPlan.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LegView', params: { legId: leg.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'LegEdit', params: { legId: leg.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(leg)"
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
        ><span id="pigeonalpoc2App.leg.delete.question" data-cy="legDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-leg-heading">Are you sure you want to delete this Leg?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-leg"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeLeg()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./leg.component.ts"></script>
