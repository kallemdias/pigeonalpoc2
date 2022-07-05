<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.leg.home.createOrEditLabel" data-cy="LegCreateUpdateHeading">Create or edit a Leg</h2>
        <div>
          <div class="form-group" v-if="leg.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="leg.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-order">Order</label>
            <input
              type="number"
              class="form-control"
              name="order"
              id="leg-order"
              data-cy="order"
              :class="{ valid: !$v.leg.order.$invalid, invalid: $v.leg.order.$invalid }"
              v-model.number="$v.leg.order.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLatDeg">Dep Lat Deg</label>
            <input
              type="number"
              class="form-control"
              name="depLatDeg"
              id="leg-depLatDeg"
              data-cy="depLatDeg"
              :class="{ valid: !$v.leg.depLatDeg.$invalid, invalid: $v.leg.depLatDeg.$invalid }"
              v-model.number="$v.leg.depLatDeg.$model"
            />
            <div v-if="$v.leg.depLatDeg.$anyDirty && $v.leg.depLatDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.leg.depLatDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.leg.depLatDeg.max"> This field cannot be longer than 90 characters. </small>
              <small class="form-text text-danger" v-if="!$v.leg.depLatDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLatMin">Dep Lat Min</label>
            <input
              type="number"
              class="form-control"
              name="depLatMin"
              id="leg-depLatMin"
              data-cy="depLatMin"
              :class="{ valid: !$v.leg.depLatMin.$invalid, invalid: $v.leg.depLatMin.$invalid }"
              v-model.number="$v.leg.depLatMin.$model"
            />
            <div v-if="$v.leg.depLatMin.$anyDirty && $v.leg.depLatMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.leg.depLatMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.leg.depLatMin.max"> This field cannot be longer than 59 characters. </small>
              <small class="form-text text-danger" v-if="!$v.leg.depLatMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLatSec">Dep Lat Sec</label>
            <input
              type="number"
              class="form-control"
              name="depLatSec"
              id="leg-depLatSec"
              data-cy="depLatSec"
              :class="{ valid: !$v.leg.depLatSec.$invalid, invalid: $v.leg.depLatSec.$invalid }"
              v-model.number="$v.leg.depLatSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLatDirection">Dep Lat Direction</label>
            <select
              class="form-control"
              name="depLatDirection"
              :class="{ valid: !$v.leg.depLatDirection.$invalid, invalid: $v.leg.depLatDirection.$invalid }"
              v-model="$v.leg.depLatDirection.$model"
              id="leg-depLatDirection"
              data-cy="depLatDirection"
            >
              <option v-for="latDirection in latDirectionValues" :key="latDirection" v-bind:value="latDirection">{{ latDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLngDeg">Dep Lng Deg</label>
            <input
              type="number"
              class="form-control"
              name="depLngDeg"
              id="leg-depLngDeg"
              data-cy="depLngDeg"
              :class="{ valid: !$v.leg.depLngDeg.$invalid, invalid: $v.leg.depLngDeg.$invalid }"
              v-model.number="$v.leg.depLngDeg.$model"
            />
            <div v-if="$v.leg.depLngDeg.$anyDirty && $v.leg.depLngDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.leg.depLngDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.leg.depLngDeg.max"> This field cannot be longer than 180 characters. </small>
              <small class="form-text text-danger" v-if="!$v.leg.depLngDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLngMin">Dep Lng Min</label>
            <input
              type="number"
              class="form-control"
              name="depLngMin"
              id="leg-depLngMin"
              data-cy="depLngMin"
              :class="{ valid: !$v.leg.depLngMin.$invalid, invalid: $v.leg.depLngMin.$invalid }"
              v-model.number="$v.leg.depLngMin.$model"
            />
            <div v-if="$v.leg.depLngMin.$anyDirty && $v.leg.depLngMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.leg.depLngMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.leg.depLngMin.max"> This field cannot be longer than 59 characters. </small>
              <small class="form-text text-danger" v-if="!$v.leg.depLngMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLngSec">Dep Lng Sec</label>
            <input
              type="number"
              class="form-control"
              name="depLngSec"
              id="leg-depLngSec"
              data-cy="depLngSec"
              :class="{ valid: !$v.leg.depLngSec.$invalid, invalid: $v.leg.depLngSec.$invalid }"
              v-model.number="$v.leg.depLngSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLngDirection">Dep Lng Direction</label>
            <select
              class="form-control"
              name="depLngDirection"
              :class="{ valid: !$v.leg.depLngDirection.$invalid, invalid: $v.leg.depLngDirection.$invalid }"
              v-model="$v.leg.depLngDirection.$model"
              id="leg-depLngDirection"
              data-cy="depLngDirection"
            >
              <option v-for="lngDirection in lngDirectionValues" :key="lngDirection" v-bind:value="lngDirection">{{ lngDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLatDisplayedValue">Dep Lat Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="depLatDisplayedValue"
              id="leg-depLatDisplayedValue"
              data-cy="depLatDisplayedValue"
              :class="{ valid: !$v.leg.depLatDisplayedValue.$invalid, invalid: $v.leg.depLatDisplayedValue.$invalid }"
              v-model="$v.leg.depLatDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLngDisplayedValue">Dep Lng Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="depLngDisplayedValue"
              id="leg-depLngDisplayedValue"
              data-cy="depLngDisplayedValue"
              :class="{ valid: !$v.leg.depLngDisplayedValue.$invalid, invalid: $v.leg.depLngDisplayedValue.$invalid }"
              v-model="$v.leg.depLngDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLatDecimal">Dep Lat Decimal</label>
            <input
              type="number"
              class="form-control"
              name="depLatDecimal"
              id="leg-depLatDecimal"
              data-cy="depLatDecimal"
              :class="{ valid: !$v.leg.depLatDecimal.$invalid, invalid: $v.leg.depLatDecimal.$invalid }"
              v-model.number="$v.leg.depLatDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-depLngDecimal">Dep Lng Decimal</label>
            <input
              type="number"
              class="form-control"
              name="depLngDecimal"
              id="leg-depLngDecimal"
              data-cy="depLngDecimal"
              :class="{ valid: !$v.leg.depLngDecimal.$invalid, invalid: $v.leg.depLngDecimal.$invalid }"
              v-model.number="$v.leg.depLngDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLatDeg">Arr Lat Deg</label>
            <input
              type="number"
              class="form-control"
              name="arrLatDeg"
              id="leg-arrLatDeg"
              data-cy="arrLatDeg"
              :class="{ valid: !$v.leg.arrLatDeg.$invalid, invalid: $v.leg.arrLatDeg.$invalid }"
              v-model.number="$v.leg.arrLatDeg.$model"
            />
            <div v-if="$v.leg.arrLatDeg.$anyDirty && $v.leg.arrLatDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.leg.arrLatDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.leg.arrLatDeg.max"> This field cannot be longer than 90 characters. </small>
              <small class="form-text text-danger" v-if="!$v.leg.arrLatDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLatMin">Arr Lat Min</label>
            <input
              type="number"
              class="form-control"
              name="arrLatMin"
              id="leg-arrLatMin"
              data-cy="arrLatMin"
              :class="{ valid: !$v.leg.arrLatMin.$invalid, invalid: $v.leg.arrLatMin.$invalid }"
              v-model.number="$v.leg.arrLatMin.$model"
            />
            <div v-if="$v.leg.arrLatMin.$anyDirty && $v.leg.arrLatMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.leg.arrLatMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.leg.arrLatMin.max"> This field cannot be longer than 59 characters. </small>
              <small class="form-text text-danger" v-if="!$v.leg.arrLatMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLatSec">Arr Lat Sec</label>
            <input
              type="number"
              class="form-control"
              name="arrLatSec"
              id="leg-arrLatSec"
              data-cy="arrLatSec"
              :class="{ valid: !$v.leg.arrLatSec.$invalid, invalid: $v.leg.arrLatSec.$invalid }"
              v-model.number="$v.leg.arrLatSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLatDirection">Arr Lat Direction</label>
            <select
              class="form-control"
              name="arrLatDirection"
              :class="{ valid: !$v.leg.arrLatDirection.$invalid, invalid: $v.leg.arrLatDirection.$invalid }"
              v-model="$v.leg.arrLatDirection.$model"
              id="leg-arrLatDirection"
              data-cy="arrLatDirection"
            >
              <option v-for="latDirection in latDirectionValues" :key="latDirection" v-bind:value="latDirection">{{ latDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLngDeg">Arr Lng Deg</label>
            <input
              type="number"
              class="form-control"
              name="arrLngDeg"
              id="leg-arrLngDeg"
              data-cy="arrLngDeg"
              :class="{ valid: !$v.leg.arrLngDeg.$invalid, invalid: $v.leg.arrLngDeg.$invalid }"
              v-model.number="$v.leg.arrLngDeg.$model"
            />
            <div v-if="$v.leg.arrLngDeg.$anyDirty && $v.leg.arrLngDeg.$invalid">
              <small class="form-text text-danger" v-if="!$v.leg.arrLngDeg.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.leg.arrLngDeg.max"> This field cannot be longer than 180 characters. </small>
              <small class="form-text text-danger" v-if="!$v.leg.arrLngDeg.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLngMin">Arr Lng Min</label>
            <input
              type="number"
              class="form-control"
              name="arrLngMin"
              id="leg-arrLngMin"
              data-cy="arrLngMin"
              :class="{ valid: !$v.leg.arrLngMin.$invalid, invalid: $v.leg.arrLngMin.$invalid }"
              v-model.number="$v.leg.arrLngMin.$model"
            />
            <div v-if="$v.leg.arrLngMin.$anyDirty && $v.leg.arrLngMin.$invalid">
              <small class="form-text text-danger" v-if="!$v.leg.arrLngMin.min"> This field should be at least 0. </small>
              <small class="form-text text-danger" v-if="!$v.leg.arrLngMin.max"> This field cannot be longer than 59 characters. </small>
              <small class="form-text text-danger" v-if="!$v.leg.arrLngMin.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLngSec">Arr Lng Sec</label>
            <input
              type="number"
              class="form-control"
              name="arrLngSec"
              id="leg-arrLngSec"
              data-cy="arrLngSec"
              :class="{ valid: !$v.leg.arrLngSec.$invalid, invalid: $v.leg.arrLngSec.$invalid }"
              v-model.number="$v.leg.arrLngSec.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLngDirection">Arr Lng Direction</label>
            <select
              class="form-control"
              name="arrLngDirection"
              :class="{ valid: !$v.leg.arrLngDirection.$invalid, invalid: $v.leg.arrLngDirection.$invalid }"
              v-model="$v.leg.arrLngDirection.$model"
              id="leg-arrLngDirection"
              data-cy="arrLngDirection"
            >
              <option v-for="lngDirection in lngDirectionValues" :key="lngDirection" v-bind:value="lngDirection">{{ lngDirection }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLatDisplayedValue">Arr Lat Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="arrLatDisplayedValue"
              id="leg-arrLatDisplayedValue"
              data-cy="arrLatDisplayedValue"
              :class="{ valid: !$v.leg.arrLatDisplayedValue.$invalid, invalid: $v.leg.arrLatDisplayedValue.$invalid }"
              v-model="$v.leg.arrLatDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLngDisplayedValue">Arr Lng Displayed Value</label>
            <input
              type="text"
              class="form-control"
              name="arrLngDisplayedValue"
              id="leg-arrLngDisplayedValue"
              data-cy="arrLngDisplayedValue"
              :class="{ valid: !$v.leg.arrLngDisplayedValue.$invalid, invalid: $v.leg.arrLngDisplayedValue.$invalid }"
              v-model="$v.leg.arrLngDisplayedValue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLatDecimal">Arr Lat Decimal</label>
            <input
              type="number"
              class="form-control"
              name="arrLatDecimal"
              id="leg-arrLatDecimal"
              data-cy="arrLatDecimal"
              :class="{ valid: !$v.leg.arrLatDecimal.$invalid, invalid: $v.leg.arrLatDecimal.$invalid }"
              v-model.number="$v.leg.arrLatDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-arrLngDecimal">Arr Lng Decimal</label>
            <input
              type="number"
              class="form-control"
              name="arrLngDecimal"
              id="leg-arrLngDecimal"
              data-cy="arrLngDecimal"
              :class="{ valid: !$v.leg.arrLngDecimal.$invalid, invalid: $v.leg.arrLngDecimal.$invalid }"
              v-model.number="$v.leg.arrLngDecimal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-checkPointDistance">Check Point Distance</label>
            <select
              class="form-control"
              name="checkPointDistance"
              :class="{ valid: !$v.leg.checkPointDistance.$invalid, invalid: $v.leg.checkPointDistance.$invalid }"
              v-model="$v.leg.checkPointDistance.$model"
              id="leg-checkPointDistance"
              data-cy="checkPointDistance"
            >
              <option v-for="checkPointDistance in checkPointDistanceValues" :key="checkPointDistance" v-bind:value="checkPointDistance">
                {{ checkPointDistance }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-checkLinePointDistance">Check Line Point Distance</label>
            <select
              class="form-control"
              name="checkLinePointDistance"
              :class="{ valid: !$v.leg.checkLinePointDistance.$invalid, invalid: $v.leg.checkLinePointDistance.$invalid }"
              v-model="$v.leg.checkLinePointDistance.$model"
              id="leg-checkLinePointDistance"
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
            <label class="form-control-label" for="leg-timeWindow">Time Window</label>
            <select
              class="form-control"
              name="timeWindow"
              :class="{ valid: !$v.leg.timeWindow.$invalid, invalid: $v.leg.timeWindow.$invalid }"
              v-model="$v.leg.timeWindow.$model"
              id="leg-timeWindow"
              data-cy="timeWindow"
            >
              <option v-for="timeWindow in timeWindowValues" :key="timeWindow" v-bind:value="timeWindow">{{ timeWindow }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="leg-racingPlan">Racing Plan</label>
            <select class="form-control" id="leg-racingPlan" data-cy="racingPlan" name="racingPlan" v-model="leg.racingPlan">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="leg.racingPlan && racingPlanOption.id === leg.racingPlan.id ? leg.racingPlan : racingPlanOption"
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
            :disabled="$v.leg.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./leg-update.component.ts"></script>
