<template>
  <div>
    <h2 id="page-heading" data-cy="WeatherDataPointHeading">
      <span id="weather-data-point-heading">Weather Data Points</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'WeatherDataPointCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-weather-data-point"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Weather Data Point </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && weatherDataPoints && weatherDataPoints.length === 0">
      <span>No weatherDataPoints found</span>
    </div>
    <div class="table-responsive" v-if="weatherDataPoints && weatherDataPoints.length > 0">
      <table class="table table-striped" aria-describedby="weatherDataPoints">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('property')">
              <span>Property</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'property'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('midNight')">
              <span>Mid Night</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'midNight'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('migNightRelevance')">
              <span>Mig Night Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'migNightRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('one')">
              <span>One</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'one'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('oneRelevance')">
              <span>One Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'oneRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('two')">
              <span>Two</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'two'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twoRelevance')">
              <span>Two Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twoRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('three')">
              <span>Three</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'three'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('threeRelevance')">
              <span>Three Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'threeRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('four')">
              <span>Four</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'four'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fourRelevance')">
              <span>Four Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fourRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('five')">
              <span>Five</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'five'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fiveRelevance')">
              <span>Five Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fiveRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('six')">
              <span>Six</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'six'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sixRelevance')">
              <span>Six Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sixRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('seven')">
              <span>Seven</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'seven'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sevenRelevance')">
              <span>Seven Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sevenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eight')">
              <span>Eight</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eight'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eightRelevance')">
              <span>Eight Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eightRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nine')">
              <span>Nine</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nine'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nineRelevance')">
              <span>Nine Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nineRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ten')">
              <span>Ten</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ten'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tenRelevance')">
              <span>Ten Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eleven')">
              <span>Eleven</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eleven'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('elevenRelevance')">
              <span>Eleven Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'elevenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twelve')">
              <span>Twelve</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twelve'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twelveRelevance')">
              <span>Twelve Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twelveRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('thirteen')">
              <span>Thirteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'thirteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('thirteenRelevance')">
              <span>Thirteen Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'thirteenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fourteen')">
              <span>Fourteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fourteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fourteenRelevance')">
              <span>Fourteen Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fourteenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fifteen')">
              <span>Fifteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fifteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fifteenRelevance')">
              <span>Fifteen Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fifteenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sixteen')">
              <span>Sixteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sixteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sixteenRelevance')">
              <span>Sixteen Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sixteenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('seventeen')">
              <span>Seventeen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'seventeen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('seventeenRelevance')">
              <span>Seventeen Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'seventeenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eighteen')">
              <span>Eighteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eighteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eighteenRelevance')">
              <span>Eighteen Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eighteenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nineteen')">
              <span>Nineteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nineteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nineteenRelevance')">
              <span>Nineteen Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nineteenRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twenty')">
              <span>Twenty</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twenty'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyRelevance')">
              <span>Twenty Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyOne')">
              <span>Twenty One</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyOne'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyOneRelevance')">
              <span>Twenty One Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyOneRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyTwo')">
              <span>Twenty Two</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyTwo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyTwoRelevance')">
              <span>Twenty Two Relevance</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyTwoRelevance'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyThree')">
              <span>Twenty Three</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyThree'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('checkPoint.id')">
              <span>Check Point</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkPoint.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="weatherDataPoint in weatherDataPoints" :key="weatherDataPoint.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WeatherDataPointView', params: { weatherDataPointId: weatherDataPoint.id } }">{{
                weatherDataPoint.id
              }}</router-link>
            </td>
            <td>{{ weatherDataPoint.property }}</td>
            <td>{{ weatherDataPoint.midNight }}</td>
            <td>{{ weatherDataPoint.migNightRelevance }}</td>
            <td>{{ weatherDataPoint.one }}</td>
            <td>{{ weatherDataPoint.oneRelevance }}</td>
            <td>{{ weatherDataPoint.two }}</td>
            <td>{{ weatherDataPoint.twoRelevance }}</td>
            <td>{{ weatherDataPoint.three }}</td>
            <td>{{ weatherDataPoint.threeRelevance }}</td>
            <td>{{ weatherDataPoint.four }}</td>
            <td>{{ weatherDataPoint.fourRelevance }}</td>
            <td>{{ weatherDataPoint.five }}</td>
            <td>{{ weatherDataPoint.fiveRelevance }}</td>
            <td>{{ weatherDataPoint.six }}</td>
            <td>{{ weatherDataPoint.sixRelevance }}</td>
            <td>{{ weatherDataPoint.seven }}</td>
            <td>{{ weatherDataPoint.sevenRelevance }}</td>
            <td>{{ weatherDataPoint.eight }}</td>
            <td>{{ weatherDataPoint.eightRelevance }}</td>
            <td>{{ weatherDataPoint.nine }}</td>
            <td>{{ weatherDataPoint.nineRelevance }}</td>
            <td>{{ weatherDataPoint.ten }}</td>
            <td>{{ weatherDataPoint.tenRelevance }}</td>
            <td>{{ weatherDataPoint.eleven }}</td>
            <td>{{ weatherDataPoint.elevenRelevance }}</td>
            <td>{{ weatherDataPoint.twelve }}</td>
            <td>{{ weatherDataPoint.twelveRelevance }}</td>
            <td>{{ weatherDataPoint.thirteen }}</td>
            <td>{{ weatherDataPoint.thirteenRelevance }}</td>
            <td>{{ weatherDataPoint.fourteen }}</td>
            <td>{{ weatherDataPoint.fourteenRelevance }}</td>
            <td>{{ weatherDataPoint.fifteen }}</td>
            <td>{{ weatherDataPoint.fifteenRelevance }}</td>
            <td>{{ weatherDataPoint.sixteen }}</td>
            <td>{{ weatherDataPoint.sixteenRelevance }}</td>
            <td>{{ weatherDataPoint.seventeen }}</td>
            <td>{{ weatherDataPoint.seventeenRelevance }}</td>
            <td>{{ weatherDataPoint.eighteen }}</td>
            <td>{{ weatherDataPoint.eighteenRelevance }}</td>
            <td>{{ weatherDataPoint.nineteen }}</td>
            <td>{{ weatherDataPoint.nineteenRelevance }}</td>
            <td>{{ weatherDataPoint.twenty }}</td>
            <td>{{ weatherDataPoint.twentyRelevance }}</td>
            <td>{{ weatherDataPoint.twentyOne }}</td>
            <td>{{ weatherDataPoint.twentyOneRelevance }}</td>
            <td>{{ weatherDataPoint.twentyTwo }}</td>
            <td>{{ weatherDataPoint.twentyTwoRelevance }}</td>
            <td>{{ weatherDataPoint.twentyThree }}</td>
            <td>
              <div v-if="weatherDataPoint.checkPoint">
                <router-link :to="{ name: 'CheckPointView', params: { checkPointId: weatherDataPoint.checkPoint.id } }">{{
                  weatherDataPoint.checkPoint.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'WeatherDataPointView', params: { weatherDataPointId: weatherDataPoint.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'WeatherDataPointEdit', params: { weatherDataPointId: weatherDataPoint.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(weatherDataPoint)"
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
        ><span id="pigeonalpoc2App.weatherDataPoint.delete.question" data-cy="weatherDataPointDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-weatherDataPoint-heading">Are you sure you want to delete this Weather Data Point?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-weatherDataPoint"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeWeatherDataPoint()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./weather-data-point.component.ts"></script>
