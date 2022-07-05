<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.weatherDataPoint.home.createOrEditLabel" data-cy="WeatherDataPointCreateUpdateHeading">
          Create or edit a WeatherDataPoint
        </h2>
        <div>
          <div class="form-group" v-if="weatherDataPoint.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="weatherDataPoint.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-property">Property</label>
            <select
              class="form-control"
              name="property"
              :class="{ valid: !$v.weatherDataPoint.property.$invalid, invalid: $v.weatherDataPoint.property.$invalid }"
              v-model="$v.weatherDataPoint.property.$model"
              id="weather-data-point-property"
              data-cy="property"
            >
              <option v-for="weatherProperty in weatherPropertyValues" :key="weatherProperty" v-bind:value="weatherProperty">
                {{ weatherProperty }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-midNight">Mid Night</label>
            <input
              type="text"
              class="form-control"
              name="midNight"
              id="weather-data-point-midNight"
              data-cy="midNight"
              :class="{ valid: !$v.weatherDataPoint.midNight.$invalid, invalid: $v.weatherDataPoint.midNight.$invalid }"
              v-model="$v.weatherDataPoint.midNight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-migNightRelevance">Mig Night Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="migNightRelevance"
              id="weather-data-point-migNightRelevance"
              data-cy="migNightRelevance"
              :class="{ valid: !$v.weatherDataPoint.migNightRelevance.$invalid, invalid: $v.weatherDataPoint.migNightRelevance.$invalid }"
              v-model="$v.weatherDataPoint.migNightRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-one">One</label>
            <input
              type="text"
              class="form-control"
              name="one"
              id="weather-data-point-one"
              data-cy="one"
              :class="{ valid: !$v.weatherDataPoint.one.$invalid, invalid: $v.weatherDataPoint.one.$invalid }"
              v-model="$v.weatherDataPoint.one.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-oneRelevance">One Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="oneRelevance"
              id="weather-data-point-oneRelevance"
              data-cy="oneRelevance"
              :class="{ valid: !$v.weatherDataPoint.oneRelevance.$invalid, invalid: $v.weatherDataPoint.oneRelevance.$invalid }"
              v-model="$v.weatherDataPoint.oneRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-two">Two</label>
            <input
              type="text"
              class="form-control"
              name="two"
              id="weather-data-point-two"
              data-cy="two"
              :class="{ valid: !$v.weatherDataPoint.two.$invalid, invalid: $v.weatherDataPoint.two.$invalid }"
              v-model="$v.weatherDataPoint.two.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twoRelevance">Two Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="twoRelevance"
              id="weather-data-point-twoRelevance"
              data-cy="twoRelevance"
              :class="{ valid: !$v.weatherDataPoint.twoRelevance.$invalid, invalid: $v.weatherDataPoint.twoRelevance.$invalid }"
              v-model="$v.weatherDataPoint.twoRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-three">Three</label>
            <input
              type="text"
              class="form-control"
              name="three"
              id="weather-data-point-three"
              data-cy="three"
              :class="{ valid: !$v.weatherDataPoint.three.$invalid, invalid: $v.weatherDataPoint.three.$invalid }"
              v-model="$v.weatherDataPoint.three.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-threeRelevance">Three Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="threeRelevance"
              id="weather-data-point-threeRelevance"
              data-cy="threeRelevance"
              :class="{ valid: !$v.weatherDataPoint.threeRelevance.$invalid, invalid: $v.weatherDataPoint.threeRelevance.$invalid }"
              v-model="$v.weatherDataPoint.threeRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-four">Four</label>
            <input
              type="text"
              class="form-control"
              name="four"
              id="weather-data-point-four"
              data-cy="four"
              :class="{ valid: !$v.weatherDataPoint.four.$invalid, invalid: $v.weatherDataPoint.four.$invalid }"
              v-model="$v.weatherDataPoint.four.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-fourRelevance">Four Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="fourRelevance"
              id="weather-data-point-fourRelevance"
              data-cy="fourRelevance"
              :class="{ valid: !$v.weatherDataPoint.fourRelevance.$invalid, invalid: $v.weatherDataPoint.fourRelevance.$invalid }"
              v-model="$v.weatherDataPoint.fourRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-five">Five</label>
            <input
              type="text"
              class="form-control"
              name="five"
              id="weather-data-point-five"
              data-cy="five"
              :class="{ valid: !$v.weatherDataPoint.five.$invalid, invalid: $v.weatherDataPoint.five.$invalid }"
              v-model="$v.weatherDataPoint.five.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-fiveRelevance">Five Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="fiveRelevance"
              id="weather-data-point-fiveRelevance"
              data-cy="fiveRelevance"
              :class="{ valid: !$v.weatherDataPoint.fiveRelevance.$invalid, invalid: $v.weatherDataPoint.fiveRelevance.$invalid }"
              v-model="$v.weatherDataPoint.fiveRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-six">Six</label>
            <input
              type="text"
              class="form-control"
              name="six"
              id="weather-data-point-six"
              data-cy="six"
              :class="{ valid: !$v.weatherDataPoint.six.$invalid, invalid: $v.weatherDataPoint.six.$invalid }"
              v-model="$v.weatherDataPoint.six.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-sixRelevance">Six Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="sixRelevance"
              id="weather-data-point-sixRelevance"
              data-cy="sixRelevance"
              :class="{ valid: !$v.weatherDataPoint.sixRelevance.$invalid, invalid: $v.weatherDataPoint.sixRelevance.$invalid }"
              v-model="$v.weatherDataPoint.sixRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-seven">Seven</label>
            <input
              type="text"
              class="form-control"
              name="seven"
              id="weather-data-point-seven"
              data-cy="seven"
              :class="{ valid: !$v.weatherDataPoint.seven.$invalid, invalid: $v.weatherDataPoint.seven.$invalid }"
              v-model="$v.weatherDataPoint.seven.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-sevenRelevance">Seven Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="sevenRelevance"
              id="weather-data-point-sevenRelevance"
              data-cy="sevenRelevance"
              :class="{ valid: !$v.weatherDataPoint.sevenRelevance.$invalid, invalid: $v.weatherDataPoint.sevenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.sevenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-eight">Eight</label>
            <input
              type="text"
              class="form-control"
              name="eight"
              id="weather-data-point-eight"
              data-cy="eight"
              :class="{ valid: !$v.weatherDataPoint.eight.$invalid, invalid: $v.weatherDataPoint.eight.$invalid }"
              v-model="$v.weatherDataPoint.eight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-eightRelevance">Eight Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="eightRelevance"
              id="weather-data-point-eightRelevance"
              data-cy="eightRelevance"
              :class="{ valid: !$v.weatherDataPoint.eightRelevance.$invalid, invalid: $v.weatherDataPoint.eightRelevance.$invalid }"
              v-model="$v.weatherDataPoint.eightRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-nine">Nine</label>
            <input
              type="text"
              class="form-control"
              name="nine"
              id="weather-data-point-nine"
              data-cy="nine"
              :class="{ valid: !$v.weatherDataPoint.nine.$invalid, invalid: $v.weatherDataPoint.nine.$invalid }"
              v-model="$v.weatherDataPoint.nine.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-nineRelevance">Nine Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="nineRelevance"
              id="weather-data-point-nineRelevance"
              data-cy="nineRelevance"
              :class="{ valid: !$v.weatherDataPoint.nineRelevance.$invalid, invalid: $v.weatherDataPoint.nineRelevance.$invalid }"
              v-model="$v.weatherDataPoint.nineRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-ten">Ten</label>
            <input
              type="text"
              class="form-control"
              name="ten"
              id="weather-data-point-ten"
              data-cy="ten"
              :class="{ valid: !$v.weatherDataPoint.ten.$invalid, invalid: $v.weatherDataPoint.ten.$invalid }"
              v-model="$v.weatherDataPoint.ten.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-tenRelevance">Ten Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="tenRelevance"
              id="weather-data-point-tenRelevance"
              data-cy="tenRelevance"
              :class="{ valid: !$v.weatherDataPoint.tenRelevance.$invalid, invalid: $v.weatherDataPoint.tenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.tenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-eleven">Eleven</label>
            <input
              type="text"
              class="form-control"
              name="eleven"
              id="weather-data-point-eleven"
              data-cy="eleven"
              :class="{ valid: !$v.weatherDataPoint.eleven.$invalid, invalid: $v.weatherDataPoint.eleven.$invalid }"
              v-model="$v.weatherDataPoint.eleven.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-elevenRelevance">Eleven Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="elevenRelevance"
              id="weather-data-point-elevenRelevance"
              data-cy="elevenRelevance"
              :class="{ valid: !$v.weatherDataPoint.elevenRelevance.$invalid, invalid: $v.weatherDataPoint.elevenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.elevenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twelve">Twelve</label>
            <input
              type="text"
              class="form-control"
              name="twelve"
              id="weather-data-point-twelve"
              data-cy="twelve"
              :class="{ valid: !$v.weatherDataPoint.twelve.$invalid, invalid: $v.weatherDataPoint.twelve.$invalid }"
              v-model="$v.weatherDataPoint.twelve.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twelveRelevance">Twelve Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="twelveRelevance"
              id="weather-data-point-twelveRelevance"
              data-cy="twelveRelevance"
              :class="{ valid: !$v.weatherDataPoint.twelveRelevance.$invalid, invalid: $v.weatherDataPoint.twelveRelevance.$invalid }"
              v-model="$v.weatherDataPoint.twelveRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-thirteen">Thirteen</label>
            <input
              type="text"
              class="form-control"
              name="thirteen"
              id="weather-data-point-thirteen"
              data-cy="thirteen"
              :class="{ valid: !$v.weatherDataPoint.thirteen.$invalid, invalid: $v.weatherDataPoint.thirteen.$invalid }"
              v-model="$v.weatherDataPoint.thirteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-thirteenRelevance">Thirteen Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="thirteenRelevance"
              id="weather-data-point-thirteenRelevance"
              data-cy="thirteenRelevance"
              :class="{ valid: !$v.weatherDataPoint.thirteenRelevance.$invalid, invalid: $v.weatherDataPoint.thirteenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.thirteenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-fourteen">Fourteen</label>
            <input
              type="text"
              class="form-control"
              name="fourteen"
              id="weather-data-point-fourteen"
              data-cy="fourteen"
              :class="{ valid: !$v.weatherDataPoint.fourteen.$invalid, invalid: $v.weatherDataPoint.fourteen.$invalid }"
              v-model="$v.weatherDataPoint.fourteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-fourteenRelevance">Fourteen Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="fourteenRelevance"
              id="weather-data-point-fourteenRelevance"
              data-cy="fourteenRelevance"
              :class="{ valid: !$v.weatherDataPoint.fourteenRelevance.$invalid, invalid: $v.weatherDataPoint.fourteenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.fourteenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-fifteen">Fifteen</label>
            <input
              type="text"
              class="form-control"
              name="fifteen"
              id="weather-data-point-fifteen"
              data-cy="fifteen"
              :class="{ valid: !$v.weatherDataPoint.fifteen.$invalid, invalid: $v.weatherDataPoint.fifteen.$invalid }"
              v-model="$v.weatherDataPoint.fifteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-fifteenRelevance">Fifteen Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="fifteenRelevance"
              id="weather-data-point-fifteenRelevance"
              data-cy="fifteenRelevance"
              :class="{ valid: !$v.weatherDataPoint.fifteenRelevance.$invalid, invalid: $v.weatherDataPoint.fifteenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.fifteenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-sixteen">Sixteen</label>
            <input
              type="text"
              class="form-control"
              name="sixteen"
              id="weather-data-point-sixteen"
              data-cy="sixteen"
              :class="{ valid: !$v.weatherDataPoint.sixteen.$invalid, invalid: $v.weatherDataPoint.sixteen.$invalid }"
              v-model="$v.weatherDataPoint.sixteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-sixteenRelevance">Sixteen Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="sixteenRelevance"
              id="weather-data-point-sixteenRelevance"
              data-cy="sixteenRelevance"
              :class="{ valid: !$v.weatherDataPoint.sixteenRelevance.$invalid, invalid: $v.weatherDataPoint.sixteenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.sixteenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-seventeen">Seventeen</label>
            <input
              type="text"
              class="form-control"
              name="seventeen"
              id="weather-data-point-seventeen"
              data-cy="seventeen"
              :class="{ valid: !$v.weatherDataPoint.seventeen.$invalid, invalid: $v.weatherDataPoint.seventeen.$invalid }"
              v-model="$v.weatherDataPoint.seventeen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-seventeenRelevance">Seventeen Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="seventeenRelevance"
              id="weather-data-point-seventeenRelevance"
              data-cy="seventeenRelevance"
              :class="{ valid: !$v.weatherDataPoint.seventeenRelevance.$invalid, invalid: $v.weatherDataPoint.seventeenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.seventeenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-eighteen">Eighteen</label>
            <input
              type="text"
              class="form-control"
              name="eighteen"
              id="weather-data-point-eighteen"
              data-cy="eighteen"
              :class="{ valid: !$v.weatherDataPoint.eighteen.$invalid, invalid: $v.weatherDataPoint.eighteen.$invalid }"
              v-model="$v.weatherDataPoint.eighteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-eighteenRelevance">Eighteen Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="eighteenRelevance"
              id="weather-data-point-eighteenRelevance"
              data-cy="eighteenRelevance"
              :class="{ valid: !$v.weatherDataPoint.eighteenRelevance.$invalid, invalid: $v.weatherDataPoint.eighteenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.eighteenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-nineteen">Nineteen</label>
            <input
              type="text"
              class="form-control"
              name="nineteen"
              id="weather-data-point-nineteen"
              data-cy="nineteen"
              :class="{ valid: !$v.weatherDataPoint.nineteen.$invalid, invalid: $v.weatherDataPoint.nineteen.$invalid }"
              v-model="$v.weatherDataPoint.nineteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-nineteenRelevance">Nineteen Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="nineteenRelevance"
              id="weather-data-point-nineteenRelevance"
              data-cy="nineteenRelevance"
              :class="{ valid: !$v.weatherDataPoint.nineteenRelevance.$invalid, invalid: $v.weatherDataPoint.nineteenRelevance.$invalid }"
              v-model="$v.weatherDataPoint.nineteenRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twenty">Twenty</label>
            <input
              type="text"
              class="form-control"
              name="twenty"
              id="weather-data-point-twenty"
              data-cy="twenty"
              :class="{ valid: !$v.weatherDataPoint.twenty.$invalid, invalid: $v.weatherDataPoint.twenty.$invalid }"
              v-model="$v.weatherDataPoint.twenty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twentyRelevance">Twenty Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="twentyRelevance"
              id="weather-data-point-twentyRelevance"
              data-cy="twentyRelevance"
              :class="{ valid: !$v.weatherDataPoint.twentyRelevance.$invalid, invalid: $v.weatherDataPoint.twentyRelevance.$invalid }"
              v-model="$v.weatherDataPoint.twentyRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twentyOne">Twenty One</label>
            <input
              type="text"
              class="form-control"
              name="twentyOne"
              id="weather-data-point-twentyOne"
              data-cy="twentyOne"
              :class="{ valid: !$v.weatherDataPoint.twentyOne.$invalid, invalid: $v.weatherDataPoint.twentyOne.$invalid }"
              v-model="$v.weatherDataPoint.twentyOne.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twentyOneRelevance">Twenty One Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="twentyOneRelevance"
              id="weather-data-point-twentyOneRelevance"
              data-cy="twentyOneRelevance"
              :class="{ valid: !$v.weatherDataPoint.twentyOneRelevance.$invalid, invalid: $v.weatherDataPoint.twentyOneRelevance.$invalid }"
              v-model="$v.weatherDataPoint.twentyOneRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twentyTwo">Twenty Two</label>
            <input
              type="text"
              class="form-control"
              name="twentyTwo"
              id="weather-data-point-twentyTwo"
              data-cy="twentyTwo"
              :class="{ valid: !$v.weatherDataPoint.twentyTwo.$invalid, invalid: $v.weatherDataPoint.twentyTwo.$invalid }"
              v-model="$v.weatherDataPoint.twentyTwo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twentyTwoRelevance">Twenty Two Relevance</label>
            <input
              type="checkbox"
              class="form-check"
              name="twentyTwoRelevance"
              id="weather-data-point-twentyTwoRelevance"
              data-cy="twentyTwoRelevance"
              :class="{ valid: !$v.weatherDataPoint.twentyTwoRelevance.$invalid, invalid: $v.weatherDataPoint.twentyTwoRelevance.$invalid }"
              v-model="$v.weatherDataPoint.twentyTwoRelevance.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-twentyThree">Twenty Three</label>
            <input
              type="text"
              class="form-control"
              name="twentyThree"
              id="weather-data-point-twentyThree"
              data-cy="twentyThree"
              :class="{ valid: !$v.weatherDataPoint.twentyThree.$invalid, invalid: $v.weatherDataPoint.twentyThree.$invalid }"
              v-model="$v.weatherDataPoint.twentyThree.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="weather-data-point-checkPoint">Check Point</label>
            <select
              class="form-control"
              id="weather-data-point-checkPoint"
              data-cy="checkPoint"
              name="checkPoint"
              v-model="weatherDataPoint.checkPoint"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  weatherDataPoint.checkPoint && checkPointOption.id === weatherDataPoint.checkPoint.id
                    ? weatherDataPoint.checkPoint
                    : checkPointOption
                "
                v-for="checkPointOption in checkPoints"
                :key="checkPointOption.id"
              >
                {{ checkPointOption.id }}
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
            :disabled="$v.weatherDataPoint.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./weather-data-point-update.component.ts"></script>
