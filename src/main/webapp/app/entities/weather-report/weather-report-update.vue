<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.weatherReport.home.createOrEditLabel" data-cy="WeatherReportCreateUpdateHeading">
          Create or edit a WeatherReport
        </h2>
        <div>
          <div class="form-group" v-if="weatherReport.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="weatherReport.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-report-initiated">Initiated</label>
            <div class="d-flex">
              <input
                id="weather-report-initiated"
                data-cy="initiated"
                type="datetime-local"
                class="form-control"
                name="initiated"
                :class="{ valid: !$v.weatherReport.initiated.$invalid, invalid: $v.weatherReport.initiated.$invalid }"
                :value="convertDateTimeFromServer($v.weatherReport.initiated.$model)"
                @change="updateInstantField('initiated', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-report-releaseDateTime">Release Date Time</label>
            <div class="d-flex">
              <input
                id="weather-report-releaseDateTime"
                data-cy="releaseDateTime"
                type="datetime-local"
                class="form-control"
                name="releaseDateTime"
                :class="{ valid: !$v.weatherReport.releaseDateTime.$invalid, invalid: $v.weatherReport.releaseDateTime.$invalid }"
                :value="convertDateTimeFromServer($v.weatherReport.releaseDateTime.$model)"
                @change="updateInstantField('releaseDateTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-report-checkpointsDistance">Checkpoints Distance</label>
            <input
              type="number"
              class="form-control"
              name="checkpointsDistance"
              id="weather-report-checkpointsDistance"
              data-cy="checkpointsDistance"
              :class="{ valid: !$v.weatherReport.checkpointsDistance.$invalid, invalid: $v.weatherReport.checkpointsDistance.$invalid }"
              v-model.number="$v.weatherReport.checkpointsDistance.$model"
            />
            <div v-if="$v.weatherReport.checkpointsDistance.$anyDirty && $v.weatherReport.checkpointsDistance.$invalid">
              <small class="form-text text-danger" v-if="!$v.weatherReport.checkpointsDistance.min">
                This field should be at least 5.
              </small>
              <small class="form-text text-danger" v-if="!$v.weatherReport.checkpointsDistance.numeric">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-report-alerts">Alerts</label>
            <input
              type="text"
              class="form-control"
              name="alerts"
              id="weather-report-alerts"
              data-cy="alerts"
              :class="{ valid: !$v.weatherReport.alerts.$invalid, invalid: $v.weatherReport.alerts.$invalid }"
              v-model="$v.weatherReport.alerts.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-report-racingPlan">Racing Plan</label>
            <select
              class="form-control"
              id="weather-report-racingPlan"
              data-cy="racingPlan"
              name="racingPlan"
              v-model="weatherReport.racingPlan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  weatherReport.racingPlan && racingPlanOption.id === weatherReport.racingPlan.id
                    ? weatherReport.racingPlan
                    : racingPlanOption
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
            :disabled="$v.weatherReport.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./weather-report-update.component.ts"></script>
