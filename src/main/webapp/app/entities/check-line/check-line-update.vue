<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.checkLine.home.createOrEditLabel" data-cy="CheckLineCreateUpdateHeading">Create or edit a CheckLine</h2>
        <div>
          <div class="form-group" v-if="checkLine.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="checkLine.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-order">Order</label>
            <input
              type="number"
              class="form-control"
              name="order"
              id="check-line-order"
              data-cy="order"
              :class="{ valid: !$v.checkLine.order.$invalid, invalid: $v.checkLine.order.$invalid }"
              v-model.number="$v.checkLine.order.$model"
            />
            <div v-if="$v.checkLine.order.$anyDirty && $v.checkLine.order.$invalid">
              <small class="form-text text-danger" v-if="!$v.checkLine.order.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.checkLine.order.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-dateTime">Date Time</label>
            <div class="d-flex">
              <input
                id="check-line-dateTime"
                data-cy="dateTime"
                type="datetime-local"
                class="form-control"
                name="dateTime"
                :class="{ valid: !$v.checkLine.dateTime.$invalid, invalid: $v.checkLine.dateTime.$invalid }"
                :value="convertDateTimeFromServer($v.checkLine.dateTime.$model)"
                @change="updateInstantField('dateTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-locationName">Location Name</label>
            <input
              type="text"
              class="form-control"
              name="locationName"
              id="check-line-locationName"
              data-cy="locationName"
              :class="{ valid: !$v.checkLine.locationName.$invalid, invalid: $v.checkLine.locationName.$invalid }"
              v-model="$v.checkLine.locationName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-latDecimal">Lat Decimal</label>
            <input
              type="number"
              class="form-control"
              name="latDecimal"
              id="check-line-latDecimal"
              data-cy="latDecimal"
              :class="{ valid: !$v.checkLine.latDecimal.$invalid, invalid: $v.checkLine.latDecimal.$invalid }"
              v-model.number="$v.checkLine.latDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-lngDecimal">Lng Decimal</label>
            <input
              type="number"
              class="form-control"
              name="lngDecimal"
              id="check-line-lngDecimal"
              data-cy="lngDecimal"
              :class="{ valid: !$v.checkLine.lngDecimal.$invalid, invalid: $v.checkLine.lngDecimal.$invalid }"
              v-model.number="$v.checkLine.lngDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-distance">Distance</label>
            <input
              type="number"
              class="form-control"
              name="distance"
              id="check-line-distance"
              data-cy="distance"
              :class="{ valid: !$v.checkLine.distance.$invalid, invalid: $v.checkLine.distance.$invalid }"
              v-model.number="$v.checkLine.distance.$model"
            />
            <div v-if="$v.checkLine.distance.$anyDirty && $v.checkLine.distance.$invalid">
              <small class="form-text text-danger" v-if="!$v.checkLine.distance.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.checkLine.distance.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-distanceDisplayedValue">Distance Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="distanceDisplayedValue"
              id="check-line-distanceDisplayedValue"
              data-cy="distanceDisplayedValue"
              :class="{ valid: !$v.checkLine.distanceDisplayedValue.$invalid, invalid: $v.checkLine.distanceDisplayedValue.$invalid }"
              v-model="$v.checkLine.distanceDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-link">Link</label>
            <input
              type="text"
              class="form-control"
              name="link"
              id="check-line-link"
              data-cy="link"
              :class="{ valid: !$v.checkLine.link.$invalid, invalid: $v.checkLine.link.$invalid }"
              v-model="$v.checkLine.link.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="check-line-weatherReport">Weather Report</label>
            <select
              class="form-control"
              id="check-line-weatherReport"
              data-cy="weatherReport"
              name="weatherReport"
              v-model="checkLine.weatherReport"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  checkLine.weatherReport && weatherReportOption.id === checkLine.weatherReport.id
                    ? checkLine.weatherReport
                    : weatherReportOption
                "
                v-for="weatherReportOption in weatherReports"
                :key="weatherReportOption.id"
              >
                {{ weatherReportOption.id }}
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
            :disabled="$v.checkLine.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./check-line-update.component.ts"></script>
