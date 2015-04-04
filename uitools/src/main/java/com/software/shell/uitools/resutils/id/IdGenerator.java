/*
 * Copyright 2015 Shell Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * File created: 2015-04-04 17:10:13
 */

package com.software.shell.uitools.resutils.id;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class, which is responsible for views id generation
 * and storing
 * <p>
 * IDs generated by {@code AAPT} have the high byte nonzero, so
 * there is no collision for using {@link IdGenerator}
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public final class IdGenerator {

	/**
	 * Logging tag
	 */
	private static final String LOG_TAG = String.format("[UI Tools][%s]", IdGenerator.class.getSimpleName());

	/**
	 * Stores the the generated id
	 */
	private static final AtomicInteger NEXT_ID = new AtomicInteger(1);

	/**
	 * Generate a value suitable for use in {@link android.view.View#setId(int)}
	 * <p>
	 * This value will not collide with ID values generated at build time by aapt for R.id
	 *
	 * @return a generated ID value
	 */
	public static int next() {
		while (true) {
			int result = NEXT_ID.get();
			int newValue = result + 1;
			if (newValue > 0x00FFFFFF) {
				newValue = 1;
			}
			if (NEXT_ID.compareAndSet(result, newValue)) {
				Log.v(LOG_TAG, "Next generated id is: " + result);
				return result;
			}
		}
	}

}
