<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.racingPlan.home.createOrEditLabel" data-cy="RacingPlanCreateUpdateHeading">Create or edit a RacingPlan</h2>
        <div>
          <div class="form-group" v-if="racingPlan.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="racingPlan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="racing-plan-name"
              data-cy="name"
              :class="{ valid: !$v.racingPlan.name.$invalid, invalid: $v.racingPlan.name.$invalid }"
              v-model="$v.racingPlan.name.$model"
              required
            />
            <div v-if="$v.racingPlan.name.$anyDirty && $v.racingPlan.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.racingPlan.name.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-assocation">Assocation</label>
            <input
              type="text"
              class="form-control"
              name="assocation"
              id="racing-plan-assocation"
              data-cy="assocation"
              :class="{ valid: !$v.racingPlan.assocation.$invalid, invalid: $v.racingPlan.assocation.$invalid }"
              v-model="$v.racingPlan.assocation.$model"
              required
            />
            <div v-if="$v.racingPlan.assocation.$anyDirty && $v.racingPlan.assocation.$invalid">
              <small class="form-text text-danger" v-if="!$v.racingPlan.assocation.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-releaseDate">Release Date</label>
            <div class="d-flex">
              <input
                id="racing-plan-releaseDate"
                data-cy="releaseDate"
                type="datetime-local"
                class="form-control"
                name="releaseDate"
                :class="{ valid: !$v.racingPlan.releaseDate.$invalid, invalid: $v.racingPlan.releaseDate.$invalid }"
                :value="convertDateTimeFromServer($v.racingPlan.releaseDate.$model)"
                @change="updateInstantField('releaseDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-releasePoint">Release Point</label>
            <input
              type="text"
              class="form-control"
              name="releasePoint"
              id="racing-plan-releasePoint"
              data-cy="releasePoint"
              :class="{ valid: !$v.racingPlan.releasePoint.$invalid, invalid: $v.racingPlan.releasePoint.$invalid }"
              v-model="$v.racingPlan.releasePoint.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-arrivalPoint">Arrival Point</label>
            <input
              type="text"
              class="form-control"
              name="arrivalPoint"
              id="racing-plan-arrivalPoint"
              data-cy="arrivalPoint"
              :class="{ valid: !$v.racingPlan.arrivalPoint.$invalid, invalid: $v.racingPlan.arrivalPoint.$invalid }"
              v-model="$v.racingPlan.arrivalPoint.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-releasePointDMS">Release Point DMS</label>
            <input
              type="text"
              class="form-control"
              name="releasePointDMS"
              id="racing-plan-releasePointDMS"
              data-cy="releasePointDMS"
              :class="{ valid: !$v.racingPlan.releasePointDMS.$invalid, invalid: $v.racingPlan.releasePointDMS.$invalid }"
              v-model="$v.racingPlan.releasePointDMS.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-arrivalPointDMS">Arrival Point DMS</label>
            <input
              type="text"
              class="form-control"
              name="arrivalPointDMS"
              id="racing-plan-arrivalPointDMS"
              data-cy="arrivalPointDMS"
              :class="{ valid: !$v.racingPlan.arrivalPointDMS.$invalid, invalid: $v.racingPlan.arrivalPointDMS.$invalid }"
              v-model="$v.racingPlan.arrivalPointDMS.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-releaseMapLink">Release Map Link</label>
            <input
              type="text"
              class="form-control"
              name="releaseMapLink"
              id="racing-plan-releaseMapLink"
              data-cy="releaseMapLink"
              :class="{ valid: !$v.racingPlan.releaseMapLink.$invalid, invalid: $v.racingPlan.releaseMapLink.$invalid }"
              v-model="$v.racingPlan.releaseMapLink.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-arrivalMapLink">Arrival Map Link</label>
            <input
              type="text"
              class="form-control"
              name="arrivalMapLink"
              id="racing-plan-arrivalMapLink"
              data-cy="arrivalMapLink"
              :class="{ valid: !$v.racingPlan.arrivalMapLink.$invalid, invalid: $v.racingPlan.arrivalMapLink.$invalid }"
              v-model="$v.racingPlan.arrivalMapLink.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-distance">Distance</label>
            <input
              type="number"
              class="form-control"
              name="distance"
              id="racing-plan-distance"
              data-cy="distance"
              :class="{ valid: !$v.racingPlan.distance.$invalid, invalid: $v.racingPlan.distance.$invalid }"
              v-model.number="$v.racingPlan.distance.$model"
            />
            <div v-if="$v.racingPlan.distance.$anyDirty && $v.racingPlan.distance.$invalid">
              <small class="form-text text-danger" v-if="!$v.racingPlan.distance.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.racingPlan.distance.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-distanceDisplayedValue">Distance Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="distanceDisplayedValue"
              id="racing-plan-distanceDisplayedValue"
              data-cy="distanceDisplayedValue"
              :class="{ valid: !$v.racingPlan.distanceDisplayedValue.$invalid, invalid: $v.racingPlan.distanceDisplayedValue.$invalid }"
              v-model="$v.racingPlan.distanceDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-checkPointDistance">Check Point Distance</label>
            <select
              class="form-control"
              name="checkPointDistance"
              :class="{ valid: !$v.racingPlan.checkPointDistance.$invalid, invalid: $v.racingPlan.checkPointDistance.$invalid }"
              v-model="$v.racingPlan.checkPointDistance.$model"
              id="racing-plan-checkPointDistance"
              data-cy="checkPointDistance"
            >
              <option v-for="checkPointDistance in checkPointDistanceValues" :key="checkPointDistance" v-bind:value="checkPointDistance">
                {{ checkPointDistance }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-checkLinedReporting">Check Lined Reporting</label>
            <input
              type="checkbox"
              class="form-check"
              name="checkLinedReporting"
              id="racing-plan-checkLinedReporting"
              data-cy="checkLinedReporting"
              :class="{ valid: !$v.racingPlan.checkLinedReporting.$invalid, invalid: $v.racingPlan.checkLinedReporting.$invalid }"
              v-model="$v.racingPlan.checkLinedReporting.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-checkLinePointDistance">Check Line Point Distance</label>
            <select
              class="form-control"
              name="checkLinePointDistance"
              :class="{ valid: !$v.racingPlan.checkLinePointDistance.$invalid, invalid: $v.racingPlan.checkLinePointDistance.$invalid }"
              v-model="$v.racingPlan.checkLinePointDistance.$model"
              id="racing-plan-checkLinePointDistance"
              data-cy="checkLinePointDistance"
            >
              <option
                v-for="checkLinePointDistance in checkLinePointDistanceValues"
                :key="checkLinePointDistance"
                v-bind:value="checkLinePointDistance"
              >
                {{ checkLinePointDistance }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-timeWindow">Time Window</label>
            <select
              class="form-control"
              name="timeWindow"
              :class="{ valid: !$v.racingPlan.timeWindow.$invalid, invalid: $v.racingPlan.timeWindow.$invalid }"
              v-model="$v.racingPlan.timeWindow.$model"
              id="racing-plan-timeWindow"
              data-cy="timeWindow"
            >
              <option v-for="timeWindow in timeWindowValues" :key="timeWindow" v-bind:value="timeWindow">{{ timeWindow }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-generationInProgress">Generation In Progress</label>
            <input
              type="checkbox"
              class="form-check"
              name="generationInProgress"
              id="racing-plan-generationInProgress"
              data-cy="generationInProgress"
              :class="{ valid: !$v.racingPlan.generationInProgress.$invalid, invalid: $v.racingPlan.generationInProgress.$invalid }"
              v-model="$v.racingPlan.generationInProgress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-generated">Generated</label>
            <input
              type="checkbox"
              class="form-check"
              name="generated"
              id="racing-plan-generated"
              data-cy="generated"
              :class="{ valid: !$v.racingPlan.generated.$invalid, invalid: $v.racingPlan.generated.$invalid }"
              v-model="$v.racingPlan.generated.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-reset">Reset</label>
            <input
              type="checkbox"
              class="form-check"
              name="reset"
              id="racing-plan-reset"
              data-cy="reset"
              :class="{ valid: !$v.racingPlan.reset.$invalid, invalid: $v.racingPlan.reset.$invalid }"
              v-model="$v.racingPlan.reset.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-progress">Progress</label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="racing-plan-progress"
              data-cy="progress"
              :class="{ valid: !$v.racingPlan.progress.$invalid, invalid: $v.racingPlan.progress.$invalid }"
              v-model.number="$v.racingPlan.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-user">User</label>
            <select class="form-control" id="racing-plan-user" data-cy="user" name="user" v-model="racingPlan.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="racingPlan.user && userOption.id === racingPlan.user.id ? racingPlan.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.login }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="racing-plan-association">Association</label>
            <select
              class="form-control"
              id="racing-plan-association"
              data-cy="association"
              name="association"
              v-model="racingPlan.association"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  racingPlan.association && associationOption.id === racingPlan.association.id ? racingPlan.association : associationOption
                "
                v-for="associationOption in associations"
                :key="associationOption.id"
              >
                {{ associationOption.id }}
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
            :disabled="$v.racingPlan.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./racing-plan-update.component.ts"></script>
