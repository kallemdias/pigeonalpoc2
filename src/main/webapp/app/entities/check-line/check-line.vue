<template>
  <div>
    <h2 id="page-heading" data-cy="CheckLineHeading">
      <span id="check-line-heading">Check Lines</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'CheckLineCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-check-line"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Check Line </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && checkLines && checkLines.length === 0">
      <span>No checkLines found</span>
    </div>
    <div class="table-responsive" v-if="checkLines && checkLines.length > 0">
      <table class="table table-striped" aria-describedby="checkLines">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('order')">
              <span>Order</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'order'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('dateTime')">
              <span>Date Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dateTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('locationName')">
              <span>Location Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'locationName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('latDecimal')">
              <span>Lat Decimal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'latDecimal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lngDecimal')">
              <span>Lng Decimal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lngDecimal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distance')">
              <span>Distance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distanceDisplayedValue')">
              <span>Distance Displayed Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distanceDisplayedValue'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('link')">
              <span>Link</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'link'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('weatherReport.id')">
              <span>Weather Report</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'weatherReport.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="checkLine in checkLines" :key="checkLine.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CheckLineView', params: { checkLineId: checkLine.id } }">{{ checkLine.id }}</router-link>
            </td>
            <td>{{ checkLine.order }}</td>
            <td>{{ checkLine.dateTime | formatDate }}</td>
            <td>{{ checkLine.locationName }}</td>
            <td>{{ checkLine.latDecimal }}</td>
            <td>{{ checkLine.lngDecimal }}</td>
            <td>{{ checkLine.distance }}</td>
            <td>{{ checkLine.distanceDisplayedValue }}</td>
            <td>{{ checkLine.link }}</td>
            <td>
              <div v-if="checkLine.weatherReport">
                <router-link :to="{ name: 'WeatherReportView', params: { weatherReportId: checkLine.weatherReport.id } }">{{
                  checkLine.weatherReport.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CheckLineView', params: { checkLineId: checkLine.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CheckLineEdit', params: { checkLineId: checkLine.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(checkLine)"
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
        ><span id="pigeonalpoc2App.checkLine.delete.question" data-cy="checkLineDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-checkLine-heading">Are you sure you want to delete this Check Line?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-checkLine"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeCheckLine()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./check-line.component.ts"></script>
