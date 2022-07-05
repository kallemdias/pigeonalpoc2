<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.distanceCalculator.home.createOrEditLabel" data-cy="DistanceCalculatorCreateUpdateHeading">
          Create or edit a DistanceCalculator
        </h2>
        <div>
          <div class="form-group" v-if="distanceCalculator.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="distanceCalculator.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLatDeg">Dep Lat Deg</label>
            <input
              type="number"
              class="form-control"
              name="depLatDeg"
              id="distance-calculator-depLatDeg"
              data-cy="depLatDeg"
              :class="{ valid: !$v.distanceCalculator.depLatDeg.$invalid, invalid: $v.distanceCalculator.depLatDeg.$invalid }"
              v-model.number="$v.distanceCalculator.depLatDeg.$model"
            />
            <div v-if="$v.distanceCalculator.depLatDeg.$anyDirty && $v.distanceCalculator.depLatDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLatDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLatDeg.max">
                This field cannot be longer than 90 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLatDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLatMin">Dep Lat Min</label>
            <input
              type="number"
              class="form-control"
              name="depLatMin"
              id="distance-calculator-depLatMin"
              data-cy="depLatMin"
              :class="{ valid: !$v.distanceCalculator.depLatMin.$invalid, invalid: $v.distanceCalculator.depLatMin.$invalid }"
              v-model.number="$v.distanceCalculator.depLatMin.$model"
            />
            <div v-if="$v.distanceCalculator.depLatMin.$anyDirty && $v.distanceCalculator.depLatMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLatMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLatMin.max">
                This field cannot be longer than 59 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLatMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLatSec">Dep Lat Sec</label>
            <input
              type="number"
              class="form-control"
              name="depLatSec"
              id="distance-calculator-depLatSec"
              data-cy="depLatSec"
              :class="{ valid: !$v.distanceCalculator.depLatSec.$invalid, invalid: $v.distanceCalculator.depLatSec.$invalid }"
              v-model.number="$v.distanceCalculator.depLatSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLatDirection">Dep Lat Direction</label>
            <select
              class="form-control"
              name="depLatDirection"
              :class="{ valid: !$v.distanceCalculator.depLatDirection.$invalid, invalid: $v.distanceCalculator.depLatDirection.$invalid }"
              v-model="$v.distanceCalculator.depLatDirection.$model"
              id="distance-calculator-depLatDirection"
              data-cy="depLatDirection"
            >
              <option v-for="latDirection in latDirectionValues" :key="latDirection" v-bind:value="latDirection">{{ latDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLngDeg">Dep Lng Deg</label>
            <input
              type="number"
              class="form-control"
              name="depLngDeg"
              id="distance-calculator-depLngDeg"
              data-cy="depLngDeg"
              :class="{ valid: !$v.distanceCalculator.depLngDeg.$invalid, invalid: $v.distanceCalculator.depLngDeg.$invalid }"
              v-model.number="$v.distanceCalculator.depLngDeg.$model"
            />
            <div v-if="$v.distanceCalculator.depLngDeg.$anyDirty && $v.distanceCalculator.depLngDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLngDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLngDeg.max">
                This field cannot be longer than 180 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLngDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLngMin">Dep Lng Min</label>
            <input
              type="number"
              class="form-control"
              name="depLngMin"
              id="distance-calculator-depLngMin"
              data-cy="depLngMin"
              :class="{ valid: !$v.distanceCalculator.depLngMin.$invalid, invalid: $v.distanceCalculator.depLngMin.$invalid }"
              v-model.number="$v.distanceCalculator.depLngMin.$model"
            />
            <div v-if="$v.distanceCalculator.depLngMin.$anyDirty && $v.distanceCalculator.depLngMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLngMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLngMin.max">
                This field cannot be longer than 59 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.depLngMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLngSec">Dep Lng Sec</label>
            <input
              type="number"
              class="form-control"
              name="depLngSec"
              id="distance-calculator-depLngSec"
              data-cy="depLngSec"
              :class="{ valid: !$v.distanceCalculator.depLngSec.$invalid, invalid: $v.distanceCalculator.depLngSec.$invalid }"
              v-model.number="$v.distanceCalculator.depLngSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLngDirection">Dep Lng Direction</label>
            <select
              class="form-control"
              name="depLngDirection"
              :class="{ valid: !$v.distanceCalculator.depLngDirection.$invalid, invalid: $v.distanceCalculator.depLngDirection.$invalid }"
              v-model="$v.distanceCalculator.depLngDirection.$model"
              id="distance-calculator-depLngDirection"
              data-cy="depLngDirection"
            >
              <option v-for="lngDirection in lngDirectionValues" :key="lngDirection" v-bind:value="lngDirection">{{ lngDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLatDisplayedValue">Dep Lat Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="depLatDisplayedValue"
              id="distance-calculator-depLatDisplayedValue"
              data-cy="depLatDisplayedValue"
              :class="{
                valid: !$v.distanceCalculator.depLatDisplayedValue.$invalid,
                invalid: $v.distanceCalculator.depLatDisplayedValue.$invalid,
              }"
              v-model="$v.distanceCalculator.depLatDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLngDisplayedValue">Dep Lng Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="depLngDisplayedValue"
              id="distance-calculator-depLngDisplayedValue"
              data-cy="depLngDisplayedValue"
              :class="{
                valid: !$v.distanceCalculator.depLngDisplayedValue.$invalid,
                invalid: $v.distanceCalculator.depLngDisplayedValue.$invalid,
              }"
              v-model="$v.distanceCalculator.depLngDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLatDecimal">Dep Lat Decimal</label>
            <input
              type="number"
              class="form-control"
              name="depLatDecimal"
              id="distance-calculator-depLatDecimal"
              data-cy="depLatDecimal"
              :class="{ valid: !$v.distanceCalculator.depLatDecimal.$invalid, invalid: $v.distanceCalculator.depLatDecimal.$invalid }"
              v-model.number="$v.distanceCalculator.depLatDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLngDecimal">Dep Lng Decimal</label>
            <input
              type="number"
              class="form-control"
              name="depLngDecimal"
              id="distance-calculator-depLngDecimal"
              data-cy="depLngDecimal"
              :class="{ valid: !$v.distanceCalculator.depLngDecimal.$invalid, invalid: $v.distanceCalculator.depLngDecimal.$invalid }"
              v-model.number="$v.distanceCalculator.depLngDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLatDeg">Arr Lat Deg</label>
            <input
              type="number"
              class="form-control"
              name="arrLatDeg"
              id="distance-calculator-arrLatDeg"
              data-cy="arrLatDeg"
              :class="{ valid: !$v.distanceCalculator.arrLatDeg.$invalid, invalid: $v.distanceCalculator.arrLatDeg.$invalid }"
              v-model.number="$v.distanceCalculator.arrLatDeg.$model"
            />
            <div v-if="$v.distanceCalculator.arrLatDeg.$anyDirty && $v.distanceCalculator.arrLatDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLatDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLatDeg.max">
                This field cannot be longer than 90 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLatDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLatMin">Arr Lat Min</label>
            <input
              type="number"
              class="form-control"
              name="arrLatMin"
              id="distance-calculator-arrLatMin"
              data-cy="arrLatMin"
              :class="{ valid: !$v.distanceCalculator.arrLatMin.$invalid, invalid: $v.distanceCalculator.arrLatMin.$invalid }"
              v-model.number="$v.distanceCalculator.arrLatMin.$model"
            />
            <div v-if="$v.distanceCalculator.arrLatMin.$anyDirty && $v.distanceCalculator.arrLatMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLatMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLatMin.max">
                This field cannot be longer than 59 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLatMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLatSec">Arr Lat Sec</label>
            <input
              type="number"
              class="form-control"
              name="arrLatSec"
              id="distance-calculator-arrLatSec"
              data-cy="arrLatSec"
              :class="{ valid: !$v.distanceCalculator.arrLatSec.$invalid, invalid: $v.distanceCalculator.arrLatSec.$invalid }"
              v-model.number="$v.distanceCalculator.arrLatSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLatDirection">Arr Lat Direction</label>
            <select
              class="form-control"
              name="arrLatDirection"
              :class="{ valid: !$v.distanceCalculator.arrLatDirection.$invalid, invalid: $v.distanceCalculator.arrLatDirection.$invalid }"
              v-model="$v.distanceCalculator.arrLatDirection.$model"
              id="distance-calculator-arrLatDirection"
              data-cy="arrLatDirection"
            >
              <option v-for="latDirection in latDirectionValues" :key="latDirection" v-bind:value="latDirection">{{ latDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLngDeg">Arr Lng Deg</label>
            <input
              type="number"
              class="form-control"
              name="arrLngDeg"
              id="distance-calculator-arrLngDeg"
              data-cy="arrLngDeg"
              :class="{ valid: !$v.distanceCalculator.arrLngDeg.$invalid, invalid: $v.distanceCalculator.arrLngDeg.$invalid }"
              v-model.number="$v.distanceCalculator.arrLngDeg.$model"
            />
            <div v-if="$v.distanceCalculator.arrLngDeg.$anyDirty && $v.distanceCalculator.arrLngDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLngDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLngDeg.max">
                This field cannot be longer than 180 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLngDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLngMin">Arr Lng Min</label>
            <input
              type="number"
              class="form-control"
              name="arrLngMin"
              id="distance-calculator-arrLngMin"
              data-cy="arrLngMin"
              :class="{ valid: !$v.distanceCalculator.arrLngMin.$invalid, invalid: $v.distanceCalculator.arrLngMin.$invalid }"
              v-model.number="$v.distanceCalculator.arrLngMin.$model"
            />
            <div v-if="$v.distanceCalculator.arrLngMin.$anyDirty && $v.distanceCalculator.arrLngMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLngMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLngMin.max">
                This field cannot be longer than 59 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.arrLngMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLngSec">Arr Lng Sec</label>
            <input
              type="number"
              class="form-control"
              name="arrLngSec"
              id="distance-calculator-arrLngSec"
              data-cy="arrLngSec"
              :class="{ valid: !$v.distanceCalculator.arrLngSec.$invalid, invalid: $v.distanceCalculator.arrLngSec.$invalid }"
              v-model.number="$v.distanceCalculator.arrLngSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLngDirection">Arr Lng Direction</label>
            <select
              class="form-control"
              name="arrLngDirection"
              :class="{ valid: !$v.distanceCalculator.arrLngDirection.$invalid, invalid: $v.distanceCalculator.arrLngDirection.$invalid }"
              v-model="$v.distanceCalculator.arrLngDirection.$model"
              id="distance-calculator-arrLngDirection"
              data-cy="arrLngDirection"
            >
              <option v-for="lngDirection in lngDirectionValues" :key="lngDirection" v-bind:value="lngDirection">{{ lngDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLatDisplayedValue">Arr Lat Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="arrLatDisplayedValue"
              id="distance-calculator-arrLatDisplayedValue"
              data-cy="arrLatDisplayedValue"
              :class="{
                valid: !$v.distanceCalculator.arrLatDisplayedValue.$invalid,
                invalid: $v.distanceCalculator.arrLatDisplayedValue.$invalid,
              }"
              v-model="$v.distanceCalculator.arrLatDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLngDisplayedValue">Arr Lng Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="arrLngDisplayedValue"
              id="distance-calculator-arrLngDisplayedValue"
              data-cy="arrLngDisplayedValue"
              :class="{
                valid: !$v.distanceCalculator.arrLngDisplayedValue.$invalid,
                invalid: $v.distanceCalculator.arrLngDisplayedValue.$invalid,
              }"
              v-model="$v.distanceCalculator.arrLngDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLatDecimal">Arr Lat Decimal</label>
            <input
              type="number"
              class="form-control"
              name="arrLatDecimal"
              id="distance-calculator-arrLatDecimal"
              data-cy="arrLatDecimal"
              :class="{ valid: !$v.distanceCalculator.arrLatDecimal.$invalid, invalid: $v.distanceCalculator.arrLatDecimal.$invalid }"
              v-model.number="$v.distanceCalculator.arrLatDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLngDecimal">Arr Lng Decimal</label>
            <input
              type="number"
              class="form-control"
              name="arrLngDecimal"
              id="distance-calculator-arrLngDecimal"
              data-cy="arrLngDecimal"
              :class="{ valid: !$v.distanceCalculator.arrLngDecimal.$invalid, invalid: $v.distanceCalculator.arrLngDecimal.$invalid }"
              v-model.number="$v.distanceCalculator.arrLngDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-distanceInMeters">Distance In Meters</label>
            <input
              type="number"
              class="form-control"
              name="distanceInMeters"
              id="distance-calculator-distanceInMeters"
              data-cy="distanceInMeters"
              :class="{ valid: !$v.distanceCalculator.distanceInMeters.$invalid, invalid: $v.distanceCalculator.distanceInMeters.$invalid }"
              v-model.number="$v.distanceCalculator.distanceInMeters.$model"
            />
            <div v-if="$v.distanceCalculator.distanceInMeters.$anyDirty && $v.distanceCalculator.distanceInMeters.$invalid">
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.distanceInMeters.min">
                This field should be at least 0.
              </small>
              <small class="form-text text-danger" v-if="!$v.distanceCalculator.distanceInMeters.numeric">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-distanceInMetersDispVal">Distance In Meters Disp Val</label>
            <input
              type="text"
              class="form-control"
              name="distanceInMetersDispVal"
              id="distance-calculator-distanceInMetersDispVal"
              data-cy="distanceInMetersDispVal"
              :class="{
                valid: !$v.distanceCalculator.distanceInMetersDispVal.$invalid,
                invalid: $v.distanceCalculator.distanceInMetersDispVal.$invalid,
              }"
              v-model="$v.distanceCalculator.distanceInMetersDispVal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-depLink">Dep Link</label>
            <input
              type="text"
              class="form-control"
              name="depLink"
              id="distance-calculator-depLink"
              data-cy="depLink"
              :class="{ valid: !$v.distanceCalculator.depLink.$invalid, invalid: $v.distanceCalculator.depLink.$invalid }"
              v-model="$v.distanceCalculator.depLink.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="distance-calculator-arrLink">Arr Link</label>
            <input
              type="text"
              class="form-control"
              name="arrLink"
              id="distance-calculator-arrLink"
              data-cy="arrLink"
              :class="{ valid: !$v.distanceCalculator.arrLink.$invalid, invalid: $v.distanceCalculator.arrLink.$invalid }"
              v-model="$v.distanceCalculator.arrLink.$model"
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
            :disabled="$v.distanceCalculator.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./distance-calculator-update.component.ts"></script>
