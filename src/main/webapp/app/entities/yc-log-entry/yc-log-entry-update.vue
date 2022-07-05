<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.ycLogEntry.home.createOrEditLabel" data-cy="YcLogEntryCreateUpdateHeading">Create or edit a YcLogEntry</h2>
        <div>
          <div class="form-group" v-if="ycLogEntry.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="ycLogEntry.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="yc-log-entry-created">Created</label>
            <div class="d-flex">
              <input
                id="yc-log-entry-created"
                data-cy="created"
                type="datetime-local"
                class="form-control"
                name="created"
                :class="{ valid: !$v.ycLogEntry.created.$invalid, invalid: $v.ycLogEntry.created.$invalid }"
                :value="convertDateTimeFromServer($v.ycLogEntry.created.$model)"
                @change="updateInstantField('created', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="yc-log-entry-serviceName">Service Name</label>
            <input
              type="text"
              class="form-control"
              name="serviceName"
              id="yc-log-entry-serviceName"
              data-cy="serviceName"
              :class="{ valid: !$v.ycLogEntry.serviceName.$invalid, invalid: $v.ycLogEntry.serviceName.$invalid }"
              v-model="$v.ycLogEntry.serviceName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="yc-log-entry-method">Method</label>
            <input
              type="text"
              class="form-control"
              name="method"
              id="yc-log-entry-method"
              data-cy="method"
              :class="{ valid: !$v.ycLogEntry.method.$invalid, invalid: $v.ycLogEntry.method.$invalid }"
              v-model="$v.ycLogEntry.method.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="yc-log-entry-step">Step</label>
            <input
              type="text"
              class="form-control"
              name="step"
              id="yc-log-entry-step"
              data-cy="step"
              :class="{ valid: !$v.ycLogEntry.step.$invalid, invalid: $v.ycLogEntry.step.$invalid }"
              v-model="$v.ycLogEntry.step.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="yc-log-entry-discription">Discription</label>
            <input
              type="text"
              class="form-control"
              name="discription"
              id="yc-log-entry-discription"
              data-cy="discription"
              :class="{ valid: !$v.ycLogEntry.discription.$invalid, invalid: $v.ycLogEntry.discription.$invalid }"
              v-model="$v.ycLogEntry.discription.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="yc-log-entry-logType">Log Type</label>
            <select
              class="form-control"
              name="logType"
              :class="{ valid: !$v.ycLogEntry.logType.$invalid, invalid: $v.ycLogEntry.logType.$invalid }"
              v-model="$v.ycLogEntry.logType.$model"
              id="yc-log-entry-logType"
              data-cy="logType"
            >
              <option v-for="logType in logTypeValues" :key="logType" v-bind:value="logType">{{ logType }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="yc-log-entry-racingPlan">Racing Plan</label>
            <select
              class="form-control"
              id="yc-log-entry-racingPlan"
              data-cy="racingPlan"
              name="racingPlan"
              v-model="ycLogEntry.racingPlan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  ycLogEntry.racingPlan && racingPlanOption.id === ycLogEntry.racingPlan.id ? ycLogEntry.racingPlan : racingPlanOption
                "
                v-for="racingPlanOption in racingPlans"
                :key="racingPlanOption.id"
              >
                {{ racingPlanOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.ycLogEntry.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./yc-log-entry-update.component.ts"></script>
