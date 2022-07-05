<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.gpsCoordinateCheck.home.createOrEditLabel" data-cy="GpsCoordinateCheckCreateUpdateHeading">
          Create or edit a GpsCoordinateCheck
        </h2>
        <div>
          <div class="form-group" v-if="gpsCoordinateCheck.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="gpsCoordinateCheck.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-latDeg">Lat Deg</label>
            <input
              type="number"
              class="form-control"
              name="latDeg"
              id="gps-coordinate-check-latDeg"
              data-cy="latDeg"
              :class="{ valid: !$v.gpsCoordinateCheck.latDeg.$invalid, invalid: $v.gpsCoordinateCheck.latDeg.$invalid }"
              v-model.number="$v.gpsCoordinateCheck.latDeg.$model"
            />
            <div v-if="$v.gpsCoordinateCheck.latDeg.$anyDirty && $v.gpsCoordinateCheck.latDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.latDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.latDeg.max">
                This field cannot be longer than 90 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.latDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-latMin">Lat Min</label>
            <input
              type="number"
              class="form-control"
              name="latMin"
              id="gps-coordinate-check-latMin"
              data-cy="latMin"
              :class="{ valid: !$v.gpsCoordinateCheck.latMin.$invalid, invalid: $v.gpsCoordinateCheck.latMin.$invalid }"
              v-model.number="$v.gpsCoordinateCheck.latMin.$model"
            />
            <div v-if="$v.gpsCoordinateCheck.latMin.$anyDirty && $v.gpsCoordinateCheck.latMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.latMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.latMin.max">
                This field cannot be longer than 59 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.latMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-latSec">Lat Sec</label>
            <input
              type="number"
              class="form-control"
              name="latSec"
              id="gps-coordinate-check-latSec"
              data-cy="latSec"
              :class="{ valid: !$v.gpsCoordinateCheck.latSec.$invalid, invalid: $v.gpsCoordinateCheck.latSec.$invalid }"
              v-model.number="$v.gpsCoordinateCheck.latSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-latDirection">Lat Direction</label>
            <select
              class="form-control"
              name="latDirection"
              :class="{ valid: !$v.gpsCoordinateCheck.latDirection.$invalid, invalid: $v.gpsCoordinateCheck.latDirection.$invalid }"
              v-model="$v.gpsCoordinateCheck.latDirection.$model"
              id="gps-coordinate-check-latDirection"
              data-cy="latDirection"
            >
              <option v-for="latDirection in latDirectionValues" :key="latDirection" v-bind:value="latDirection">{{ latDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-lngDeg">Lng Deg</label>
            <input
              type="number"
              class="form-control"
              name="lngDeg"
              id="gps-coordinate-check-lngDeg"
              data-cy="lngDeg"
              :class="{ valid: !$v.gpsCoordinateCheck.lngDeg.$invalid, invalid: $v.gpsCoordinateCheck.lngDeg.$invalid }"
              v-model.number="$v.gpsCoordinateCheck.lngDeg.$model"
            />
            <div v-if="$v.gpsCoordinateCheck.lngDeg.$anyDirty && $v.gpsCoordinateCheck.lngDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.lngDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.lngDeg.max">
                This field cannot be longer than 180 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.lngDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-lngMin">Lng Min</label>
            <input
              type="number"
              class="form-control"
              name="lngMin"
              id="gps-coordinate-check-lngMin"
              data-cy="lngMin"
              :class="{ valid: !$v.gpsCoordinateCheck.lngMin.$invalid, invalid: $v.gpsCoordinateCheck.lngMin.$invalid }"
              v-model.number="$v.gpsCoordinateCheck.lngMin.$model"
            />
            <div v-if="$v.gpsCoordinateCheck.lngMin.$anyDirty && $v.gpsCoordinateCheck.lngMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.lngMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.lngMin.max">
                This field cannot be longer than 59 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.gpsCoordinateCheck.lngMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-lngSec">Lng Sec</label>
            <input
              type="number"
              class="form-control"
              name="lngSec"
              id="gps-coordinate-check-lngSec"
              data-cy="lngSec"
              :class="{ valid: !$v.gpsCoordinateCheck.lngSec.$invalid, invalid: $v.gpsCoordinateCheck.lngSec.$invalid }"
              v-model.number="$v.gpsCoordinateCheck.lngSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-lngDirection">Lng Direction</label>
            <select
              class="form-control"
              name="lngDirection"
              :class="{ valid: !$v.gpsCoordinateCheck.lngDirection.$invalid, invalid: $v.gpsCoordinateCheck.lngDirection.$invalid }"
              v-model="$v.gpsCoordinateCheck.lngDirection.$model"
              id="gps-coordinate-check-lngDirection"
              data-cy="lngDirection"
            >
              <option v-for="lngDirection in lngDirectionValues" :key="lngDirection" v-bind:value="lngDirection">{{ lngDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-latDisplayedValue">Lat Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="latDisplayedValue"
              id="gps-coordinate-check-latDisplayedValue"
              data-cy="latDisplayedValue"
              :class="{
                valid: !$v.gpsCoordinateCheck.latDisplayedValue.$invalid,
                invalid: $v.gpsCoordinateCheck.latDisplayedValue.$invalid,
              }"
              v-model="$v.gpsCoordinateCheck.latDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-lngDisplayedValue">Lng Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="lngDisplayedValue"
              id="gps-coordinate-check-lngDisplayedValue"
              data-cy="lngDisplayedValue"
              :class="{
                valid: !$v.gpsCoordinateCheck.lngDisplayedValue.$invalid,
                invalid: $v.gpsCoordinateCheck.lngDisplayedValue.$invalid,
              }"
              v-model="$v.gpsCoordinateCheck.lngDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-latDecimal">Lat Decimal</label>
            <input
              type="number"
              class="form-control"
              name="latDecimal"
              id="gps-coordinate-check-latDecimal"
              data-cy="latDecimal"
              :class="{ valid: !$v.gpsCoordinateCheck.latDecimal.$invalid, invalid: $v.gpsCoordinateCheck.latDecimal.$invalid }"
              v-model.number="$v.gpsCoordinateCheck.latDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-lngDecimal">Lng Decimal</label>
            <input
              type="number"
              class="form-control"
              name="lngDecimal"
              id="gps-coordinate-check-lngDecimal"
              data-cy="lngDecimal"
              :class="{ valid: !$v.gpsCoordinateCheck.lngDecimal.$invalid, invalid: $v.gpsCoordinateCheck.lngDecimal.$invalid }"
              v-model.number="$v.gpsCoordinateCheck.lngDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="gps-coordinate-check-link">Link</label>
            <input
              type="text"
              class="form-control"
              name="link"
              id="gps-coordinate-check-link"
              data-cy="link"
              :class="{ valid: !$v.gpsCoordinateCheck.link.$invalid, invalid: $v.gpsCoordinateCheck.link.$invalid }"
              v-model="$v.gpsCoordinateCheck.link.$model"
            />
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
            :disabled="$v.gpsCoordinateCheck.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./gps-coordinate-check-update.component.ts"></script>
