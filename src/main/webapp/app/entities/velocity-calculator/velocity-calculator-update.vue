<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.velocityCalculator.home.createOrEditLabel" data-cy="VelocityCalculatorCreateUpdateHeading">
          Create or edit a VelocityCalculator
        </h2>
        <div>
          <div class="form-group" v-if="velocityCalculator.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="velocityCalculator.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="velocity-calculator-releaseDateTime">Release Date Time</label>
            <div class="d-flex">
              <input
                id="velocity-calculator-releaseDateTime"
                data-cy="releaseDateTime"
                type="datetime-local"
                class="form-control"
                name="releaseDateTime"
                :class="{ valid: !$v.velocityCalculator.releaseDateTime.$invalid, invalid: $v.velocityCalculator.releaseDateTime.$invalid }"
                :value="convertDateTimeFromServer($v.velocityCalculator.releaseDateTime.$model)"
                @change="updateInstantField('releaseDateTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="velocity-calculator-arrivalDateTime">Arrival Date Time</label>
            <div class="d-flex">
              <input
                id="velocity-calculator-arrivalDateTime"
                data-cy="arrivalDateTime"
                type="datetime-local"
                class="form-control"
                name="arrivalDateTime"
                :class="{ valid: !$v.velocityCalculator.arrivalDateTime.$invalid, invalid: $v.velocityCalculator.arrivalDateTime.$invalid }"
                :value="convertDateTimeFromServer($v.velocityCalculator.arrivalDateTime.$model)"
                @change="updateInstantField('arrivalDateTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="velocity-calculator-distanceKM">Distance KM</label>
            <input
              type="number"
              class="form-control"
              name="distanceKM"
              id="velocity-calculator-distanceKM"
              data-cy="distanceKM"
              :class="{ valid: !$v.velocityCalculator.distanceKM.$invalid, invalid: $v.velocityCalculator.distanceKM.$invalid }"
              v-model.number="$v.velocityCalculator.distanceKM.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="velocity-calculator-distanceM">Distance M</label>
            <input
              type="number"
              class="form-control"
              name="distanceM"
              id="velocity-calculator-distanceM"
              data-cy="distanceM"
              :class="{ valid: !$v.velocityCalculator.distanceM.$invalid, invalid: $v.velocityCalculator.distanceM.$invalid }"
              v-model.number="$v.velocityCalculator.distanceM.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="velocity-calculator-velocity">Velocity</label>
            <input
              type="number"
              class="form-control"
              name="velocity"
              id="velocity-calculator-velocity"
              data-cy="velocity"
              :class="{ valid: !$v.velocityCalculator.velocity.$invalid, invalid: $v.velocityCalculator.velocity.$invalid }"
              v-model.number="$v.velocityCalculator.velocity.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="velocity-calculator-velocityDispVal">Velocity Disp Val</label>
            <input
              type="text"
              class="form-control"
              name="velocityDispVal"
              id="velocity-calculator-velocityDispVal"
              data-cy="velocityDispVal"
              :class="{ valid: !$v.velocityCalculator.velocityDispVal.$invalid, invalid: $v.velocityCalculator.velocityDispVal.$invalid }"
              v-model="$v.velocityCalculator.velocityDispVal.$model"
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
            :disabled="$v.velocityCalculator.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./velocity-calculator-update.component.ts"></script>
