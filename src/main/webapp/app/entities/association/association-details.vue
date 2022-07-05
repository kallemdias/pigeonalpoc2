<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="association">
        <h2 class="jh-entity-heading" data-cy="associationDetailsHeading"><span>Association</span> {{ association.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Name</span>
          </dt>
          <dd>
            <span>{{ association.name }}</span>
          </dd>
          <dt>
            <span>Short Name</span>
          </dt>
          <dd>
            <span>{{ association.shortName }}</span>
          </dd>
          <dt>
            <span>Email</span>
          </dt>
          <dd>
            <span>{{ association.email }}</span>
          </dd>
          <dt>
            <span>Phone</span>
          </dt>
          <dd>
            <span>{{ association.phone }}</span>
          </dd>
          <dt>
            <span>Address Line 1</span>
          </dt>
          <dd>
            <span>{{ association.addressLine1 }}</span>
          </dd>
          <dt>
            <span>Address Line 2</span>
          </dt>
          <dd>
            <span>{{ association.addressLine2 }}</span>
          </dd>
          <dt>
            <span>City</span>
          </dt>
          <dd>
            <span>{{ association.city }}</span>
          </dd>
          <dt>
            <span>Country</span>
          </dt>
          <dd>
            <span>{{ association.country }}</span>
          </dd>
          <dt>
            <span>Fb Link</span>
          </dt>
          <dd>
            <span>{{ association.fbLink }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="association.id"
          :to="{ name: 'AssociationEdit', params: { associationId: association.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./association-details.component.ts"></script>
