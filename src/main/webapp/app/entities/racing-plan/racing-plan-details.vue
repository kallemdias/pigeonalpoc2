<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="racingPlan">
        <h2 class="jh-entity-heading" data-cy="racingPlanDetailsHeading"><span>RacingPlan</span> {{ racingPlan.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Name</span>
          </dt>
          <dd>
            <span>{{ racingPlan.name }}</span>
          </dd>
          <dt>
            <span>Assocation</span>
          </dt>
          <dd>
            <span>{{ racingPlan.assocation }}</span>
          </dd>
          <dt>
            <span>Release Date</span>
          </dt>
          <dd>
            <span>{{ racingPlan.releaseDate | formatDate }}</span>
          </dd>
          <dt>
            <span>Release Point</span>
          </dt>
          <dd>
            <span>{{ racingPlan.releasePoint }}</span>
          </dd>
          <dt>
            <span>Arrival Point</span>
          </dt>
          <dd>
            <span>{{ racingPlan.arrivalPoint }}</span>
          </dd>
          <dt>
            <span>Release Point DMS</span>
          </dt>
          <dd>
            <span>{{ racingPlan.releasePointDMS }}</span>
          </dd>
          <dt>
            <span>Arrival Point DMS</span>
          </dt>
          <dd>
            <span>{{ racingPlan.arrivalPointDMS }}</span>
          </dd>
          <dt>
            <span>Release Map Link</span>
          </dt>
          <dd>
            <span>{{ racingPlan.releaseMapLink }}</span>
          </dd>
          <dt>
            <span>Arrival Map Link</span>
          </dt>
          <dd>
            <span>{{ racingPlan.arrivalMapLink }}</span>
          </dd>
          <dt>
            <span>Distance</span>
          </dt>
          <dd>
            <span>{{ racingPlan.distance }}</span>
          </dd>
          <dt>
            <span>Distance Displayed Value</span>
          </dt>
          <dd>
            <span>{{ racingPlan.distanceDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Check Point Distance</span>
          </dt>
          <dd>
            <span>{{ racingPlan.checkPointDistance }}</span>
          </dd>
          <dt>
            <span>Check Lined Reporting</span>
          </dt>
          <dd>
            <span>{{ racingPlan.checkLinedReporting }}</span>
          </dd>
          <dt>
            <span>Check Line Point Distance</span>
          </dt>
          <dd>
            <span>{{ racingPlan.checkLinePointDistance }}</span>
          </dd>
          <dt>
            <span>Time Window</span>
          </dt>
          <dd>
            <span>{{ racingPlan.timeWindow }}</span>
          </dd>
          <dt>
            <span>Generation In Progress</span>
          </dt>
          <dd>
            <span>{{ racingPlan.generationInProgress }}</span>
          </dd>
          <dt>
            <span>Generated</span>
          </dt>
          <dd>
            <span>{{ racingPlan.generated }}</span>
          </dd>
          <dt>
            <span>Reset</span>
          </dt>
          <dd>
            <span>{{ racingPlan.reset }}</span>
          </dd>
          <dt>
            <span>Progress</span>
          </dt>
          <dd>
            <span>{{ racingPlan.progress }}</span>
          </dd>
          <dt>
            <span>User</span>
          </dt>
          <dd>
            {{ racingPlan.user ? racingPlan.user.login : '' }}
          </dd>
          <dt>
            <span>Association</span>
          </dt>
          <dd>
            <div v-if="racingPlan.association">
              <router-link :to="{ name: 'AssociationView', params: { associationId: racingPlan.association.id } }">{{
                racingPlan.association.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="racingPlan.id"
          :to="{ name: 'RacingPlanEdit', params: { racingPlanId: racingPlan.id } }"
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

<script lang="ts" src="./racing-plan-details.component.ts"></script>
