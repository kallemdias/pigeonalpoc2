<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.association.home.createOrEditLabel" data-cy="AssociationCreateUpdateHeading">
          Create or edit a Association
        </h2>
        <div>
          <div class="form-group" v-if="association.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="association.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="association-name"
              data-cy="name"
              :class="{ valid: !$v.association.name.$invalid, invalid: $v.association.name.$invalid }"
              v-model="$v.association.name.$model"
              required
            />
            <div v-if="$v.association.name.$anyDirty && $v.association.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.association.name.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-shortName">Short Name</label>
            <input
              type="text"
              class="form-control"
              name="shortName"
              id="association-shortName"
              data-cy="shortName"
              :class="{ valid: !$v.association.shortName.$invalid, invalid: $v.association.shortName.$invalid }"
              v-model="$v.association.shortName.$model"
              required
            />
            <div v-if="$v.association.shortName.$anyDirty && $v.association.shortName.$invalid">
              <small class="form-text text-danger" v-if="!$v.association.shortName.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="association-email"
              data-cy="email"
              :class="{ valid: !$v.association.email.$invalid, invalid: $v.association.email.$invalid }"
              v-model="$v.association.email.$model"
            />
            <div v-if="$v.association.email.$anyDirty && $v.association.email.$invalid">
              <small class="form-text text-danger" v-if="!$v.association.email.pattern">
                This field should follow pattern for "Email".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-phone">Phone</label>
            <input
              type="text"
              class="form-control"
              name="phone"
              id="association-phone"
              data-cy="phone"
              :class="{ valid: !$v.association.phone.$invalid, invalid: $v.association.phone.$invalid }"
              v-model="$v.association.phone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-addressLine1">Address Line 1</label>
            <input
              type="text"
              class="form-control"
              name="addressLine1"
              id="association-addressLine1"
              data-cy="addressLine1"
              :class="{ valid: !$v.association.addressLine1.$invalid, invalid: $v.association.addressLine1.$invalid }"
              v-model="$v.association.addressLine1.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-addressLine2">Address Line 2</label>
            <input
              type="text"
              class="form-control"
              name="addressLine2"
              id="association-addressLine2"
              data-cy="addressLine2"
              :class="{ valid: !$v.association.addressLine2.$invalid, invalid: $v.association.addressLine2.$invalid }"
              v-model="$v.association.addressLine2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-city">City</label>
            <input
              type="text"
              class="form-control"
              name="city"
              id="association-city"
              data-cy="city"
              :class="{ valid: !$v.association.city.$invalid, invalid: $v.association.city.$invalid }"
              v-model="$v.association.city.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-country">Country</label>
            <input
              type="text"
              class="form-control"
              name="country"
              id="association-country"
              data-cy="country"
              :class="{ valid: !$v.association.country.$invalid, invalid: $v.association.country.$invalid }"
              v-model="$v.association.country.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="association-fbLink">Fb Link</label>
            <input
              type="text"
              class="form-control"
              name="fbLink"
              id="association-fbLink"
              data-cy="fbLink"
              :class="{ valid: !$v.association.fbLink.$invalid, invalid: $v.association.fbLink.$invalid }"
              v-model="$v.association.fbLink.$model"
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
            :disabled="$v.association.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./association-update.component.ts"></script>
