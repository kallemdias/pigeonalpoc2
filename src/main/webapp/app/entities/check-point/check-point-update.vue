<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.checkPoint.home.createOrEditLabel" data-cy="CheckPointCreateUpdateHeading">Create or edit a CheckPoint</h2>
        <div>
          <div class="form-group" v-if="checkPoint.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="checkPoint.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-order">Order</label>
            <input
              type="number"
              class="form-control"
              name="order"
              id="check-point-order"
              data-cy="order"
              :class="{ valid: !$v.checkPoint.order.$invalid, invalid: $v.checkPoint.order.$invalid }"
              v-model.number="$v.checkPoint.order.$model"
            />
            <div v-if="$v.checkPoint.order.$anyDirty && $v.checkPoint.order.$invalid">
              <small class="form-text text-danger" v-if="!$v.checkPoint.order.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.checkPoint.order.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-dateTime">Date Time</label>
            <div class="d-flex">
              <input
                id="check-point-dateTime"
                data-cy="dateTime"
                type="datetime-local"
                class="form-control"
                name="dateTime"
                :class="{ valid: !$v.checkPoint.dateTime.$invalid, invalid: $v.checkPoint.dateTime.$invalid }"
                :value="convertDateTimeFromServer($v.checkPoint.dateTime.$model)"
                @change="updateInstantField('dateTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-locationName">Location Name</label>
            <input
              type="text"
              class="form-control"
              name="locationName"
              id="check-point-locationName"
              data-cy="locationName"
              :class="{ valid: !$v.checkPoint.locationName.$invalid, invalid: $v.checkPoint.locationName.$invalid }"
              v-model="$v.checkPoint.locationName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-latDecimal">Lat Decimal</label>
            <input
              type="number"
              class="form-control"
              name="latDecimal"
              id="check-point-latDecimal"
              data-cy="latDecimal"
              :class="{ valid: !$v.checkPoint.latDecimal.$invalid, invalid: $v.checkPoint.latDecimal.$invalid }"
              v-model.number="$v.checkPoint.latDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-lngDecimal">Lng Decimal</label>
            <input
              type="number"
              class="form-control"
              name="lngDecimal"
              id="check-point-lngDecimal"
              data-cy="lngDecimal"
              :class="{ valid: !$v.checkPoint.lngDecimal.$invalid, invalid: $v.checkPoint.lngDecimal.$invalid }"
              v-model.number="$v.checkPoint.lngDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-distance">Distance</label>
            <input
              type="number"
              class="form-control"
              name="distance"
              id="check-point-distance"
              data-cy="distance"
              :class="{ valid: !$v.checkPoint.distance.$invalid, invalid: $v.checkPoint.distance.$invalid }"
              v-model.number="$v.checkPoint.distance.$model"
            />
            <div v-if="$v.checkPoint.distance.$anyDirty && $v.checkPoint.distance.$invalid">
              <small class="form-text text-danger" v-if="!$v.checkPoint.distance.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.checkPoint.distance.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-distanceDisplayedValue">Distance Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="distanceDisplayedValue"
              id="check-point-distanceDisplayedValue"
              data-cy="distanceDisplayedValue"
              :class="{ valid: !$v.checkPoint.distanceDisplayedValue.$invalid, invalid: $v.checkPoint.distanceDisplayedValue.$invalid }"
              v-model="$v.checkPoint.distanceDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-link">Link</label>
            <input
              type="text"
              class="form-control"
              name="link"
              id="check-point-link"
              data-cy="link"
              :class="{ valid: !$v.checkPoint.link.$invalid, invalid: $v.checkPoint.link.$invalid }"
              v-model="$v.checkPoint.link.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-alerts">Alerts</label>
            <input
              type="text"
              class="form-control"
              name="alerts"
              id="check-point-alerts"
              data-cy="alerts"
              :class="{ valid: !$v.checkPoint.alerts.$invalid, invalid: $v.checkPoint.alerts.$invalid }"
              v-model="$v.checkPoint.alerts.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-point-checkLine">Check Line</label>
            <select class="form-control" id="check-point-checkLine" data-cy="checkLine" name="checkLine" v-model="checkPoint.checkLine">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  checkPoint.checkLine && checkLineOption.id === checkPoint.checkLine.id ? checkPoint.checkLine : checkLineOption
                "
                v-for="checkLineOption in checkLines"
                :key="checkLineOption.id"
              >
                {{ checkLineOption.id }}
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
            :disabled="$v.checkPoint.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./check-point-update.component.ts"></script>
