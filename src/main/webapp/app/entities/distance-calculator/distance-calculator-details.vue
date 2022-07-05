<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="distanceCalculator">
        <h2 class="jh-entity-heading" data-cy="distanceCalculatorDetailsHeading">
          <span>DistanceCalculator</span> {{ distanceCalculator.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Dep Lat Deg</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLatDeg }}</span>
          </dd>
          <dt>
            <span>Dep Lat Min</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLatMin }}</span>
          </dd>
          <dt>
            <span>Dep Lat Sec</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLatSec }}</span>
          </dd>
          <dt>
            <span>Dep Lat Direction</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLatDirection }}</span>
          </dd>
          <dt>
            <span>Dep Lng Deg</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLngDeg }}</span>
          </dd>
          <dt>
            <span>Dep Lng Min</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLngMin }}</span>
          </dd>
          <dt>
            <span>Dep Lng Sec</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLngSec }}</span>
          </dd>
          <dt>
            <span>Dep Lng Direction</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLngDirection }}</span>
          </dd>
          <dt>
            <span>Dep Lat Displayed Value</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLatDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Dep Lng Displayed Value</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLngDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Dep Lat Decimal</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLatDecimal }}</span>
          </dd>
          <dt>
            <span>Dep Lng Decimal</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLngDecimal }}</span>
          </dd>
          <dt>
            <span>Arr Lat Deg</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLatDeg }}</span>
          </dd>
          <dt>
            <span>Arr Lat Min</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLatMin }}</span>
          </dd>
          <dt>
            <span>Arr Lat Sec</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLatSec }}</span>
          </dd>
          <dt>
            <span>Arr Lat Direction</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLatDirection }}</span>
          </dd>
          <dt>
            <span>Arr Lng Deg</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLngDeg }}</span>
          </dd>
          <dt>
            <span>Arr Lng Min</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLngMin }}</span>
          </dd>
          <dt>
            <span>Arr Lng Sec</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLngSec }}</span>
          </dd>
          <dt>
            <span>Arr Lng Direction</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLngDirection }}</span>
          </dd>
          <dt>
            <span>Arr Lat Displayed Value</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLatDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Arr Lng Displayed Value</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLngDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Arr Lat Decimal</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLatDecimal }}</span>
          </dd>
          <dt>
            <span>Arr Lng Decimal</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLngDecimal }}</span>
          </dd>
          <dt>
            <span>Distance In Meters</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.distanceInMeters }}</span>
          </dd>
          <dt>
            <span>Distance In Meters Disp Val</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.distanceInMetersDispVal }}</span>
          </dd>
          <dt>
            <span>Dep Link</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.depLink }}</span>
          </dd>
          <dt>
            <span>Arr Link</span>
          </dt>
          <dd>
            <span>{{ distanceCalculator.arrLink }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="distanceCalculator.id"
          :to="{ name: 'DistanceCalculatorEdit', params: { distanceCalculatorId: distanceCalculator.id } }"
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

<script lang="ts" src="./distance-calculator-details.component.ts"></script>
