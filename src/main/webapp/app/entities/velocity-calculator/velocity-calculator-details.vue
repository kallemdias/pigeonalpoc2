<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="velocityCalculator">
        <h2 class="jh-entity-heading" data-cy="velocityCalculatorDetailsHeading">
          <span>VelocityCalculator</span> {{ velocityCalculator.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Release Date Time</span>
          </dt>
          <dd>
            <span>{{ velocityCalculator.releaseDateTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Arrival Date Time</span>
          </dt>
          <dd>
            <span>{{ velocityCalculator.arrivalDateTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Distance KM</span>
          </dt>
          <dd>
            <span>{{ velocityCalculator.distanceKM }}</span>
          </dd>
          <dt>
            <span>Distance M</span>
          </dt>
          <dd>
            <span>{{ velocityCalculator.distanceM }}</span>
          </dd>
          <dt>
            <span>Velocity</span>
          </dt>
          <dd>
            <span>{{ velocityCalculator.velocity }}</span>
          </dd>
          <dt>
            <span>Velocity Disp Val</span>
          </dt>
          <dd>
            <span>{{ velocityCalculator.velocityDispVal }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="velocityCalculator.id"
          :to="{ name: 'VelocityCalculatorEdit', params: { velocityCalculatorId: velocityCalculator.id } }"
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

<script lang="ts" src="./velocity-calculator-details.component.ts"></script>
