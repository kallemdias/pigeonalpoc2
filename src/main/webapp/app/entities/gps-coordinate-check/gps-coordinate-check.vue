<template>
  <div>
    <h2 id="page-heading" data-cy="GpsCoordinateCheckHeading">
      <span id="gps-coordinate-check-heading">Gps Coordinate Checks</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'GpsCoordinateCheckCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-gps-coordinate-check"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Gps Coordinate Check </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && gpsCoordinateChecks && gpsCoordinateChecks.length === 0">
      <span>No gpsCoordinateChecks found</span>
    </div>
    <div class="table-responsive" v-if="gpsCoordinateChecks && gpsCoordinateChecks.length > 0">
      <table class="table table-striped" aria-describedby="gpsCoordinateChecks">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('latDeg')">
              <span>Lat Deg</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'latDeg'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('latMin')">
              <span>Lat Min</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'latMin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('latSec')">
              <span>Lat Sec</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'latSec'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('latDirection')">
              <span>Lat Direction</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'latDirection'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lngDeg')">
              <span>Lng Deg</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lngDeg'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lngMin')">
              <span>Lng Min</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lngMin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lngSec')">
              <span>Lng Sec</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lngSec'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lngDirection')">
              <span>Lng Direction</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lngDirection'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('latDisplayedValue')">
              <span>Lat Displayed Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'latDisplayedValue'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lngDisplayedValue')">
              <span>Lng Displayed Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lngDisplayedValue'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('latDecimal')">
              <span>Lat Decimal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'latDecimal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lngDecimal')">
              <span>Lng Decimal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lngDecimal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('link')">
              <span>Link</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'link'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="gpsCoordinateCheck in gpsCoordinateChecks" :key="gpsCoordinateCheck.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'GpsCoordinateCheckView', params: { gpsCoordinateCheckId: gpsCoordinateCheck.id } }">{{
                gpsCoordinateCheck.id
              }}</router-link>
            </td>
            <td>{{ gpsCoordinateCheck.latDeg }}</td>
            <td>{{ gpsCoordinateCheck.latMin }}</td>
            <td>{{ gpsCoordinateCheck.latSec }}</td>
            <td>{{ gpsCoordinateCheck.latDirection }}</td>
            <td>{{ gpsCoordinateCheck.lngDeg }}</td>
            <td>{{ gpsCoordinateCheck.lngMin }}</td>
            <td>{{ gpsCoordinateCheck.lngSec }}</td>
            <td>{{ gpsCoordinateCheck.lngDirection }}</td>
            <td>{{ gpsCoordinateCheck.latDisplayedValue }}</td>
            <td>{{ gpsCoordinateCheck.lngDisplayedValue }}</td>
            <td>{{ gpsCoordinateCheck.latDecimal }}</td>
            <td>{{ gpsCoordinateCheck.lngDecimal }}</td>
            <td>{{ gpsCoordinateCheck.link }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'GpsCoordinateCheckView', params: { gpsCoordinateCheckId: gpsCoordinateCheck.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'GpsCoordinateCheckEdit', params: { gpsCoordinateCheckId: gpsCoordinateCheck.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(gpsCoordinateCheck)"
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
        ><span id="pigeonalpoc2App.gpsCoordinateCheck.delete.question" data-cy="gpsCoordinateCheckDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-gpsCoordinateCheck-heading">Are you sure you want to delete this Gps Coordinate Check?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-gpsCoordinateCheck"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeGpsCoordinateCheck()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./gps-coordinate-check.component.ts"></script>
