<template>
  <div>
    <h2 id="page-heading" data-cy="YcLogEntryHeading">
      <span id="yc-log-entry-heading">Yc Log Entries</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'YcLogEntryCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-yc-log-entry"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Yc Log Entry </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && ycLogEntries && ycLogEntries.length === 0">
      <span>No ycLogEntries found</span>
    </div>
    <div class="table-responsive" v-if="ycLogEntries && ycLogEntries.length > 0">
      <table class="table table-striped" aria-describedby="ycLogEntries">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('created')">
              <span>Created</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'created'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('serviceName')">
              <span>Service Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'serviceName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('method')">
              <span>Method</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'method'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('step')">
              <span>Step</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'step'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('discription')">
              <span>Discription</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'discription'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('logType')">
              <span>Log Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'logType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('racingPlan.id')">
              <span>Racing Plan</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'racingPlan.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="ycLogEntry in ycLogEntries" :key="ycLogEntry.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'YcLogEntryView', params: { ycLogEntryId: ycLogEntry.id } }">{{ ycLogEntry.id }}</router-link>
            </td>
            <td>{{ ycLogEntry.created | formatDate }}</td>
            <td>{{ ycLogEntry.serviceName }}</td>
            <td>{{ ycLogEntry.method }}</td>
            <td>{{ ycLogEntry.step }}</td>
            <td>{{ ycLogEntry.discription }}</td>
            <td>{{ ycLogEntry.logType }}</td>
            <td>
              <div v-if="ycLogEntry.racingPlan">
                <router-link :to="{ name: 'RacingPlanView', params: { racingPlanId: ycLogEntry.racingPlan.id } }">{{
                  ycLogEntry.racingPlan.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'YcLogEntryView', params: { ycLogEntryId: ycLogEntry.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'YcLogEntryEdit', params: { ycLogEntryId: ycLogEntry.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(ycLogEntry)"
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
        ><span id="pigeonalpoc2App.ycLogEntry.delete.question" data-cy="ycLogEntryDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-ycLogEntry-heading">Are you sure you want to delete this Yc Log Entry?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-ycLogEntry"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeYcLogEntry()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./yc-log-entry.component.ts"></script>
