<template>
  <div>
    <h2 id="page-heading" data-cy="WeatherReportHeading">
      <span id="weather-report-heading">Weather Reports</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'WeatherReportCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-weather-report"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Weather Report </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && weatherReports && weatherReports.length === 0">
      <span>No weatherReports found</span>
    </div>
    <div class="table-responsive" v-if="weatherReports && weatherReports.length > 0">
      <table class="table table-striped" aria-describedby="weatherReports">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('initiated')">
              <span>Initiated</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'initiated'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('releaseDateTime')">
              <span>Release Date Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'releaseDateTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('checkpointsDistance')">
              <span>Checkpoints Distance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkpointsDistance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('alerts')">
              <span>Alerts</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'alerts'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('racingPlan.id')">
              <span>Racing Plan</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'racingPlan.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="weatherReport in weatherReports" :key="weatherReport.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WeatherReportView', params: { weatherReportId: weatherReport.id } }">{{
                weatherReport.id
              }}</router-link>
            </td>
            <td>{{ weatherReport.initiated | formatDate }}</td>
            <td>{{ weatherReport.releaseDateTime | formatDate }}</td>
            <td>{{ weatherReport.checkpointsDistance }}</td>
            <td>{{ weatherReport.alerts }}</td>
            <td>
              <div v-if="weatherReport.racingPlan">
                <router-link :to="{ name: 'RacingPlanView', params: { racingPlanId: weatherReport.racingPlan.id } }">{{
                  weatherReport.racingPlan.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'WeatherReportView', params: { weatherReportId: weatherReport.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'WeatherReportEdit', params: { weatherReportId: weatherReport.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(weatherReport)"
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
        ><span id="pigeonalpoc2App.weatherReport.delete.question" data-cy="weatherReportDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-weatherReport-heading">Are you sure you want to delete this Weather Report?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-weatherReport"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeWeatherReport()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./weather-report.component.ts"></script>
