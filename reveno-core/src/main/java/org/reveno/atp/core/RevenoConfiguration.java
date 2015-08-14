/** 
 *  Copyright (c) 2015 The original author or authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.reveno.atp.core;

import org.reveno.atp.api.ChannelOptions;
import org.reveno.atp.api.Configuration;

public class RevenoConfiguration implements Configuration {

	@Override
	public SnapshotConfiguration snapshotting() {
		return revenoSnapshotting();
	}
	public RevenoSnapshotConfiguration revenoSnapshotting() {
		return snapshotting;
	}
	
	@Override
	public DisruptorConfiguration disruptor() {
		return revenoDisruptor();
	}
	public RevenoDisruptorConfiguration revenoDisruptor() {
		return disruptor;
	}
	
	@Override
	public void mutableModelFailover(MutableModelFailover mutableModelFailover) {
		this.mutableModelFailover = mutableModelFailover;
	}
	public MutableModelFailover mutableModelFailover() {
		return mutableModelFailover;
	}

	@Override
	public void modelType(ModelType modelType) {
		this.modelType = modelType;
	}
	public ModelType modelType() {
		return modelType;
	}

	@Override
	public void cpuConsumption(CpuConsumption cpuConsumption) {
		this.cpuConsumption = cpuConsumption;
	}

    @Override
    public void preallocationSize(long size) {
        this.preallocationSize = size;
    }
    public long preallocationSize() {
        return this.preallocationSize;
    }

	@Override
	public void volumes(int volumes) {
		this.volumes = volumes;
	}
	public int volumes() {
		return volumes;
	}

	@Override
	public void channelOptions(ChannelOptions channelOptions) {
		this.channelOptions = channelOptions;
	}
	public ChannelOptions channelOptions() {
		return channelOptions;
	}

    public CpuConsumption cpuConsumption() {
		return cpuConsumption;
	}
	
	protected RevenoSnapshotConfiguration snapshotting = new RevenoSnapshotConfiguration();
	protected RevenoDisruptorConfiguration disruptor = new RevenoDisruptorConfiguration();
	protected CpuConsumption cpuConsumption = CpuConsumption.NORMAL;
	protected ChannelOptions channelOptions = ChannelOptions.BUFFERING_VM;
	protected ModelType modelType = ModelType.IMMUTABLE;
	protected MutableModelFailover mutableModelFailover = MutableModelFailover.SNAPSHOTS;
    protected long preallocationSize = 0L;
	protected int volumes = 3;
	
	public static class RevenoSnapshotConfiguration implements SnapshotConfiguration {

		@Override
		public void snapshotAtShutdown(boolean takeSnapshot) {
			this.snapshotAtShutdown = takeSnapshot;
		}
		public boolean snapshotAtShutdown() {
			return snapshotAtShutdown;
		}

		@Override
		public void snapshotEvery(long transactionCount) {
			this.snapshotEvery = transactionCount;
		}
		public long snapshotEvery() {
			return snapshotEvery;
		}
		
		private boolean snapshotAtShutdown = false;
		private long snapshotEvery = -1;
		
	}
	
	public static class RevenoDisruptorConfiguration implements DisruptorConfiguration {

		@Override
		public void bufferSize(int bufferSize) {
			if (Integer.bitCount(bufferSize) != 1) {
				throw new IllegalArgumentException("Disruptor buffer size must be of power of 2.");
			}
			this.bufferSize = bufferSize;
		}
		public int bufferSize() {
			return bufferSize;
		}
		
		private int bufferSize = 1024;
		
	}
	
}