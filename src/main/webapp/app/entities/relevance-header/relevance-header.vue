<template>
  <div>
    <h2 id="page-heading" data-cy="RelevanceHeaderHeading">
      <span id="relevance-header-heading">Relevance Headers</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'RelevanceHeaderCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-relevance-header"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Relevance Header </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && relevanceHeaders && relevanceHeaders.length === 0">
      <span>No relevanceHeaders found</span>
    </div>
    <div class="table-responsive" v-if="relevanceHeaders && relevanceHeaders.length > 0">
      <table class="table table-striped" aria-describedby="relevanceHeaders">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('midnight')">
              <span>Midnight</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'midnight'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('one')">
              <span>One</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'one'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('two')">
              <span>Two</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'two'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('three')">
              <span>Three</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'three'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('four')">
              <span>Four</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'four'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('five')">
              <span>Five</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'five'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('six')">
              <span>Six</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'six'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('seven')">
              <span>Seven</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'seven'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eight')">
              <span>Eight</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eight'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nine')">
              <span>Nine</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nine'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ten')">
              <span>Ten</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ten'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eleven')">
              <span>Eleven</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eleven'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twelve')">
              <span>Twelve</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twelve'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('thirteen')">
              <span>Thirteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'thirteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fourteen')">
              <span>Fourteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fourteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fifteen')">
              <span>Fifteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fifteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sixteen')">
              <span>Sixteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sixteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('seventeen')">
              <span>Seventeen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'seventeen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eighteen')">
              <span>Eighteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eighteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nineteen')">
              <span>Nineteen</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nineteen'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twenty')">
              <span>Twenty</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twenty'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyOne')">
              <span>Twenty One</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyOne'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyTwo')">
              <span>Twenty Two</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyTwo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('twentyThree')">
              <span>Twenty Three</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'twentyThree'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('weatherDataPoint.id')">
              <span>Weather Data Point</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'weatherDataPoint.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="relevanceHeader in relevanceHeaders" :key="relevanceHeader.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RelevanceHeaderView', params: { relevanceHeaderId: relevanceHeader.id } }">{{
                relevanceHeader.id
              }}</router-link>
            </td>
            <td>{{ relevanceHeader.midnight }}</td>
            <td>{{ relevanceHeader.one }}</td>
            <td>{{ relevanceHeader.two }}</td>
            <td>{{ relevanceHeader.three }}</td>
            <td>{{ relevanceHeader.four }}</td>
            <td>{{ relevanceHeader.five }}</td>
            <td>{{ relevanceHeader.six }}</td>
            <td>{{ relevanceHeader.seven }}</td>
            <td>{{ relevanceHeader.eight }}</td>
            <td>{{ relevanceHeader.nine }}</td>
            <td>{{ relevanceHeader.ten }}</td>
            <td>{{ relevanceHeader.eleven }}</td>
            <td>{{ relevanceHeader.twelve }}</td>
            <td>{{ relevanceHeader.thirteen }}</td>
            <td>{{ relevanceHeader.fourteen }}</td>
            <td>{{ relevanceHeader.fifteen }}</td>
            <td>{{ relevanceHeader.sixteen }}</td>
            <td>{{ relevanceHeader.seventeen }}</td>
            <td>{{ relevanceHeader.eighteen }}</td>
            <td>{{ relevanceHeader.nineteen }}</td>
            <td>{{ relevanceHeader.twenty }}</td>
            <td>{{ relevanceHeader.twentyOne }}</td>
            <td>{{ relevanceHeader.twentyTwo }}</td>
            <td>{{ relevanceHeader.twentyThree }}</td>
            <td>
              <div v-if="relevanceHeader.weatherDataPoint">
                <router-link :to="{ name: 'WeatherDataPointView', params: { weatherDataPointId: relevanceHeader.weatherDataPoint.id } }">{{
                  relevanceHeader.weatherDataPoint.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RelevanceHeaderView', params: { relevanceHeaderId: relevanceHeader.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RelevanceHeaderEdit', params: { relevanceHeaderId: relevanceHeader.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(relevanceHeader)"
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
        ><span id="pigeonalpoc2App.relevanceHeader.delete.question" data-cy="relevanceHeaderDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-relevanceHeader-heading">Are you sure you want to delete this Relevance Header?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-relevanceHeader"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeRelevanceHeader()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./relevance-header.component.ts"></script>
