<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="leg">
        <h2 class="jh-entity-heading" data-cy="legDetailsHeading"><span>Leg</span> {{ leg.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Order</span>
          </dt>
          <dd>
            <span>{{ leg.order }}</span>
          </dd>
          <dt>
            <span>Dep Lat Deg</span>
          </dt>
          <dd>
            <span>{{ leg.depLatDeg }}</span>
          </dd>
          <dt>
            <span>Dep Lat Min</span>
          </dt>
          <dd>
            <span>{{ leg.depLatMin }}</span>
          </dd>
          <dt>
            <span>Dep Lat Sec</span>
          </dt>
          <dd>
            <span>{{ leg.depLatSec }}</span>
          </dd>
          <dt>
            <span>Dep Lat Direction</span>
          </dt>
          <dd>
            <span>{{ leg.depLatDirection }}</span>
          </dd>
          <dt>
            <span>Dep Lng Deg</span>
          </dt>
          <dd>
            <span>{{ leg.depLngDeg }}</span>
          </dd>
          <dt>
            <span>Dep Lng Min</span>
          </dt>
          <dd>
            <span>{{ leg.depLngMin }}</span>
          </dd>
          <dt>
            <span>Dep Lng Sec</span>
          </dt>
          <dd>
            <span>{{ leg.depLngSec }}</span>
          </dd>
          <dt>
            <span>Dep Lng Direction</span>
          </dt>
          <dd>
            <span>{{ leg.depLngDirection }}</span>
          </dd>
          <dt>
            <span>Dep Lat Displayed Value</span>
          </dt>
          <dd>
            <span>{{ leg.depLatDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Dep Lng Displayed Value</span>
          </dt>
          <dd>
            <span>{{ leg.depLngDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Dep Lat Decimal</span>
          </dt>
          <dd>
            <span>{{ leg.depLatDecimal }}</span>
          </dd>
          <dt>
            <span>Dep Lng Decimal</span>
          </dt>
          <dd>
            <span>{{ leg.depLngDecimal }}</span>
          </dd>
          <dt>
            <span>Arr Lat Deg</span>
          </dt>
          <dd>
            <span>{{ leg.arrLatDeg }}</span>
          </dd>
          <dt>
            <span>Arr Lat Min</span>
          </dt>
          <dd>
            <span>{{ leg.arrLatMin }}</span>
          </dd>
          <dt>
            <span>Arr Lat Sec</span>
          </dt>
          <dd>
            <span>{{ leg.arrLatSec }}</span>
          </dd>
          <dt>
            <span>Arr Lat Direction</span>
          </dt>
          <dd>
            <span>{{ leg.arrLatDirection }}</span>
          </dd>
          <dt>
            <span>Arr Lng Deg</span>
          </dt>
          <dd>
            <span>{{ leg.arrLngDeg }}</span>
          </dd>
          <dt>
            <span>Arr Lng Min</span>
          </dt>
          <dd>
            <span>{{ leg.arrLngMin }}</span>
          </dd>
          <dt>
            <span>Arr Lng Sec</span>
          </dt>
          <dd>
            <span>{{ leg.arrLngSec }}</span>
          </dd>
          <dt>
            <span>Arr Lng Direction</span>
          </dt>
          <dd>
            <span>{{ leg.arrLngDirection }}</span>
          </dd>
          <dt>
            <span>Arr Lat Displayed Value</span>
          </dt>
          <dd>
            <span>{{ leg.arrLatDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Arr Lng Displayed Value</span>
          </dt>
          <dd>
            <span>{{ leg.arrLngDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Arr Lat Decimal</span>
          </dt>
          <dd>
            <span>{{ leg.arrLatDecimal }}</span>
          </dd>
          <dt>
            <span>Arr Lng Decimal</span>
          </dt>
          <dd>
            <span>{{ leg.arrLngDecimal }}</span>
          </dd>
          <dt>
            <span>Check Point Distance</span>
          </dt>
          <dd>
            <span>{{ leg.checkPointDistance }}</span>
          </dd>
          <dt>
            <span>Check Line Point Distance</span>
          </dt>
          <dd>
            <span>{{ leg.checkLinePointDistance }}</span>
          </dd>
          <dt>
            <span>Time Window</span>
          </dt>
          <dd>
            <span>{{ leg.timeWindow }}</span>
          </dd>
          <dt>
            <span>Racing Plan</span>
          </dt>
          <dd>
            <div v-if="leg.racingPlan">
              <router-link :to="{ name: 'RacingPlanView', params: { racingPlanId: leg.racingPlan.id } }">{{
                leg.racingPlan.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="leg.id" :to="{ name: 'LegEdit', params: { legId: leg.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./leg-details.component.ts"></script>
